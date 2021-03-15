package de.uni_stuttgart.it_rex.quiz.web.rest.written;

import de.uni_stuttgart.it_rex.quiz.QuizServiceApp;
import de.uni_stuttgart.it_rex.quiz.config.TestSecurityConfiguration;
import de.uni_stuttgart.it_rex.quiz.domain.enumeration.QUESTIONTYPE;
import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Question;
import de.uni_stuttgart.it_rex.quiz.repository.written.QuestionRepository;
import de.uni_stuttgart.it_rex.quiz.service.written.QuestionService;
import de.uni_stuttgart.it_rex.quiz.web.rest.TestUtil;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.written.QuestionMapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link QuestionResource} REST controller.
 */
@SpringBootTest(classes = { QuizServiceApp.class, TestSecurityConfiguration.class })
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class QuestionResourceIT {

    private static final UUID DEFAULT_COURSEID               = UUID.randomUUID();
    private static final QUESTIONTYPE DEFAULT_TYPE           = QUESTIONTYPE.SINGLE_CHOICE;
    private static final String DEFAULT_QUESTION             = "Best Number?";
    private static final Map<String, String> DEFAULT_CHOICES = new HashMap<String, String>() {{ put("0","Pi"); put("1","42"); }};
    private static final String DEFAULT_SOLUTION             = "1";

    private static final UUID UPDATED_COURSEID               = UUID.randomUUID();
    private static final QUESTIONTYPE UPDATED_TYPE           = QUESTIONTYPE.SINGLE_CHOICE;
    private static final String UPDATED_QUESTION             = "Best Number?";
    private static final Map<String, String> UPDATED_CHOICES = new HashMap<String, String>() {{ put("0","Pi"); put("1","42"); }};
    private static final String UPDATED_SOLUTION             = "1";

    @Autowired
    private QuestionRepository questionRepository;

    @Mock
    private QuestionRepository questionRepositoryMock;

    @Autowired
    private QuestionMapper questionMapper;

    @Mock
    private QuestionService questionServiceMock;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private MockMvc restQuestionMockMvc;

    private Question question;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createEntity() {
        Question question = new Question();
        question.setCourseId(DEFAULT_COURSEID);
        question.setQuestion(DEFAULT_QUESTION);
        return question;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createUpdatedEntity() {
        Question question = new Question();
        question.setCourseId(UPDATED_COURSEID);
        question.setQuestion(UPDATED_QUESTION);
        return question;
    }

    @BeforeEach
    public void initTest() {
        questionRepository.deleteAll();
        question = createEntity();
    }

    @Test
    public void createQuestion() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();
        // Create the Question
        QuestionDTO QuestionDTO = questionMapper.toDto(question);
        restQuestionMockMvc.perform(post("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(QuestionDTO)))
            .andExpect(status().isCreated());

        // Validate the Question in the database
        List<Question> QuestionList = questionRepository.findAll();
        assertThat(QuestionList).hasSize(databaseSizeBeforeCreate + 1);
        Question testQuestion = QuestionList.get(QuestionList.size() - 1);
        assertThat(testQuestion.getCourseId()).isEqualTo(DEFAULT_COURSEID);
        assertThat(testQuestion.getQuestion()).isEqualTo(DEFAULT_QUESTION);
    }

    @Test
    public void createQuestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();

        // Create the Question with an existing ID
        question.setId(UUID.randomUUID());
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionMockMvc.perform(post("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> QuestionList = questionRepository.findAll();
        assertThat(QuestionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllQuestionS() throws Exception {
        // Initialize the database
        questionRepository.save(question);

        // Get all the QuestionList
        restQuestionMockMvc.perform(get("/api/questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(question.getId().toString())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSEID.toString())))
            .andExpect(jsonPath("$.[*].question").value(hasItem(DEFAULT_QUESTION.toString())));
    }

    @Test
    public void getQuestion() throws Exception {
        // Initialize the database
        questionRepository.save(question);

        // Get the Question
        restQuestionMockMvc.perform(get("/api/questions/{id}", question.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(question.getId().toString()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSEID.toString()))
            .andExpect(jsonPath("$.question").value(DEFAULT_QUESTION));
    }

    @Test
    public void getNonExistingQuestion() throws Exception {
        // Get the Question
        restQuestionMockMvc.perform(get("/api/questions/{id}", UUID.randomUUID()))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateQuestion() throws Exception {
        // Initialize the database
        questionRepository.save(question);

        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Update the Question
        Question updatedQuestion = questionRepository.findById(question.getId()).get();
        updatedQuestion.setCourseId(UPDATED_COURSEID);
        updatedQuestion.setQuestion(UPDATED_QUESTION);
        QuestionDTO QuestionDTO = questionMapper.toDto(updatedQuestion);

        restQuestionMockMvc.perform(put("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(QuestionDTO)))
            .andExpect(status().isOk());

        // Validate the Question in the database
        List<Question> QuestionList = questionRepository.findAll();
        assertThat(QuestionList).hasSize(databaseSizeBeforeUpdate);
        Question testQuestion = QuestionList.get(QuestionList.size() - 1);
        assertThat(testQuestion.getCourseId()).isEqualTo(UPDATED_COURSEID);
        assertThat(testQuestion.getQuestion()).isEqualTo(UPDATED_QUESTION);
    }

    @Test
    public void updateNonExistingQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Create the Question
        QuestionDTO QuestionDTO = questionMapper.toDto(question);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionMockMvc.perform(put("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(QuestionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> QuestionList = questionRepository.findAll();
        assertThat(QuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteQuestion() throws Exception {
        // Initialize the database
        questionRepository.save(question);

        int databaseSizeBeforeDelete = questionRepository.findAll().size();

        // Delete the Question
        restQuestionMockMvc.perform(delete("/api/questions/{id}", question.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Question> QuestionList = questionRepository.findAll();
        assertThat(QuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

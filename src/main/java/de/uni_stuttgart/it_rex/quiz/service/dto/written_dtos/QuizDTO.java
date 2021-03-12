package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class QuizDTO implements Serializable {
  /**
   * Identifier.
   */
  private String id;

  /**
   * Title of the chapter.
   */
  private String name;

  /**
   * Course id.
   */
  private UUID courseId;

  /**
   * List of questions
   */
  private List<QuestionDTO> questions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}

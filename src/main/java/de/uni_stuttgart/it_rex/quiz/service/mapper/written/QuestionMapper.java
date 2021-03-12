package de.uni_stuttgart.it_rex.quiz.service.mapper.written;


import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Question;
import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.EntityMapper;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionMapper extends EntityMapper<QuestionDTO, Question> {

    @Mapping(target = "removeQuiz", ignore = true)
    @Mapping(target = "quizzes", ignore = true)
    Question toEntity(QuestionDTO dto);

    @Mapping(target = "quizIds", source = "quizzes")
    QuestionDTO toDto(Question entity);

    default UUID quizToUuid(Quiz quiz) {
        return quiz.getId();
    }
}

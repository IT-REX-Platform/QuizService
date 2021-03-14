package de.uni_stuttgart.it_rex.quiz.service.mapper.written;


import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionSingleChoice;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionSingleChoiceDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.EntityMapper;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionSingleChoiceMapper extends EntityMapper<QuestionSingleChoiceDTO, QuestionSingleChoice> {

    @Mapping(target = "removeQuiz", ignore = true)
    @Mapping(target = "removeQuizzes", ignore = true)
    @Mapping(target = "quizIds", ignore = true)
    QuestionSingleChoice toEntity(QuestionSingleChoiceDTO dto);

    QuestionSingleChoiceDTO toDto(QuestionSingleChoice entity);

}

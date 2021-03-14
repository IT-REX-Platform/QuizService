package de.uni_stuttgart.it_rex.quiz.service.mapper.written;


import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionMultipleChoice;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionMultipleChoiceDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.EntityMapper;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionMultipleChoiceMapper extends EntityMapper<QuestionMultipleChoiceDTO, QuestionMultipleChoice> {

    @Mapping(target = "removeQuiz", ignore = true)
    @Mapping(target = "removeQuizzes", ignore = true)
    @Mapping(target = "quizIds", ignore = true)
    QuestionMultipleChoice toEntity(QuestionMultipleChoiceDTO dto);

    QuestionMultipleChoiceDTO toDto(QuestionMultipleChoice entity);

}

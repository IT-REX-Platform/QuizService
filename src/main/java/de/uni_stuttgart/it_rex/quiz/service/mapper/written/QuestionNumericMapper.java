package de.uni_stuttgart.it_rex.quiz.service.mapper.written;


import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionNumeric;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionNumericDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.EntityMapper;


import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionNumericMapper extends EntityMapper<QuestionNumericDTO, QuestionNumeric> {

    @Mapping(target = "removeQuiz", ignore = true)
    @Mapping(target = "removeQuizzes", ignore = true)
    @Mapping(target = "quizIds", ignore = true)
    QuestionNumeric toEntity(QuestionNumericDTO dto);

    QuestionNumericDTO toDto(QuestionNumeric entity);

}

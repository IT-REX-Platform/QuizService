package de.uni_stuttgart.it_rex.quiz.service.mapper.written;


import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Question;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.EntityMapper;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionMultipleChoice;
import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionNumeric;
import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionSingleChoice;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionMultipleChoiceDTO;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionNumericDTO;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionSingleChoiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionMapper extends EntityMapper<QuestionDTO, Question> {

    // @Mapping(target = "removeQuiz", ignore = true)
    // @Mapping(target = "removeQuizzes", ignore = true)
    // @Mapping(target = "quizIds", ignore = true)
    default Question toEntity(QuestionDTO dto) {
        if (dto instanceof QuestionSingleChoiceDTO) {
            return this.toEntity((QuestionSingleChoiceDTO)dto);
        }
        else if (dto instanceof QuestionMultipleChoiceDTO) {
            return this.toEntity((QuestionMultipleChoiceDTO)dto);
        }
        else if (dto instanceof QuestionNumericDTO) {
            return this.toEntity((QuestionNumericDTO)dto);
        }
        return this.toEntity(dto);
    }

    default QuestionDTO toDto(Question entity) {
        if (entity instanceof QuestionSingleChoice) {
            return this.toDto((QuestionSingleChoice)entity);
        }
        else if (entity instanceof QuestionMultipleChoice) {
            return this.toDto((QuestionMultipleChoice)entity);
        }
        else if (entity instanceof QuestionNumeric) {
            return this.toDto((QuestionNumeric)entity);
        }
        return this.toDto(entity);
    }

    QuestionSingleChoice toEntity(QuestionSingleChoiceDTO dto);
    QuestionSingleChoiceDTO toDto(QuestionSingleChoice entity);
    
    QuestionMultipleChoice toEntity(QuestionMultipleChoiceDTO dto);
    QuestionMultipleChoiceDTO toDto(QuestionMultipleChoice entity);
    
    QuestionNumeric toEntity(QuestionNumericDTO dto);
    QuestionNumericDTO toDto(QuestionNumeric entity);


}

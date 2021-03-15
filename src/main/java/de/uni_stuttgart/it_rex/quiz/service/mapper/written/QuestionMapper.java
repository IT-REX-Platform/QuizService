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

import java.util.List;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuestionMapper {

    // ----- Type switches -----
    @Named(value = "toEntity")
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
        return this.baseToEntity(dto);
    }

    @Named(value = "toDto")
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
        return this.baseToDto(entity);
    }

    // Iterable Mappings
    @IterableMapping(qualifiedByName = "toEntity")
    List<Question> toEntity(List<QuestionDTO> dtoList);
    @IterableMapping(qualifiedByName = "toDto")
    List<QuestionDTO> toDto(List<Question> entityList);

    // Question Base Mappings
    @Mapping(target = "removeQuiz", ignore = true)
    @Mapping(target = "removeQuizzes", ignore = true)
    Question baseToEntity(QuestionDTO dto);

    QuestionDTO baseToDto(Question entity);

    // ----- Question Specific Mappings -----
    // QuestionSingleChoice
    @InheritConfiguration(name = "baseToEntity")
    QuestionSingleChoice toEntity(QuestionSingleChoiceDTO dto);
    
    @InheritConfiguration(name = "baseToDto")
    QuestionSingleChoiceDTO toDto(QuestionSingleChoice entity);
    
    
    // QuestionMultipleChoice
    @InheritConfiguration(name = "baseToEntity")
    QuestionMultipleChoice toEntity(QuestionMultipleChoiceDTO dto);
    
    @InheritConfiguration(name = "baseToDto")
    QuestionMultipleChoiceDTO toDto(QuestionMultipleChoice entity);
    
    
    // QuestionNumeric
    @InheritConfiguration(name = "baseToEntity")
    QuestionNumeric toEntity(QuestionNumericDTO dto);

    @InheritConfiguration(name = "baseToDto")
    QuestionNumericDTO toDto(QuestionNumeric entity);

}

package de.uni_stuttgart.it_rex.quiz.service.mapper.written;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuizDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.EntityMapper;

import java.util.List;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Quiz} and its DTO {@link QuizDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface QuizMapper extends EntityMapper<QuizDTO, Quiz> {

    @Named(value = "toEntity")
    @Mapping(target = "removeQuestion", ignore = true)
    @Mapping(target = "removeQuestions", ignore = true)
    @Mapping(target = "questions", qualifiedByName = "toEntity")
    Quiz toEntity(QuizDTO dto);
    
    @Named(value = "toDto")
    @Mapping(target = "questions", qualifiedByName = "toDto")
    QuizDTO toDto(Quiz dto);

    @Mapping(target = "removeQuestion", ignore = true)
    @Mapping(target = "removeQuestions", ignore = true)
    @Mapping(target = "questions", ignore = true)
    Quiz toEntityWithoutQuestions(QuizDTO dto);

    // Iterable Mappings
    @IterableMapping(qualifiedByName = "toEntity")
    List<Quiz> toEntity(List<QuizDTO> dtoList);
    @IterableMapping(qualifiedByName = "toDto")
    List<QuizDTO> toDto(List<Quiz> entityList);
}

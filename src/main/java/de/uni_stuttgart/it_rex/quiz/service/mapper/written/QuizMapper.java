package de.uni_stuttgart.it_rex.quiz.service.mapper.written;


import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuizDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.EntityMapper;

import java.util.UUID;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Quiz} and its DTO {@link QuizDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuizMapper extends EntityMapper<QuizDTO, Quiz> {

    // default UUID idToId(String id) {
    //     if (id == null) {
    //         return null;
    //     }
    //     return UUID.fromString(id);
    // }

    // default String idToId(UUID id) {
    //     if (id == null) {
    //         return null;
    //     }
    //     return id.toString();
    // }
}

package de.uni_stuttgart.it_rex.quiz.repository.written;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Question;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Question entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionRepository extends MongoRepository<Question, UUID> {

    List<Question> findByCourseId(UUID courseId);

}

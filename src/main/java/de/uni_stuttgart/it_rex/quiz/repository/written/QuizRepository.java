package de.uni_stuttgart.it_rex.quiz.repository.written;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

// QuerydslPredicateExecutor
// QuerydslMongoPredicateExecutor

/**
 * Spring Data MongoDB repository for the Quiz entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuizRepository extends MongoRepository<Quiz, UUID> {

    List<Quiz> findByCourseId(UUID courseId);
    void deleteByCourseId(UUID courseId);

    List<Quiz> findByIdIn(List<UUID> ids);
    void deleteByIdIn(List<UUID> ids);
}


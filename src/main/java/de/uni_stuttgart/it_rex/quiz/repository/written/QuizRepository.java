package de.uni_stuttgart.it_rex.quiz.repository.written;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Quiz entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuizRepository extends MongoRepository<Quiz, UUID> {
}


package de.uni_stuttgart.it_rex.quiz.repository;

import de.uni_stuttgart.it_rex.quiz.domain.Authority;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {
}

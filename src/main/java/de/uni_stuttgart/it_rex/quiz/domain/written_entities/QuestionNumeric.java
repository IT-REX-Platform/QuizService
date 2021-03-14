package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Question.
 */
@Document(collection = "questions")
@TypeAlias("QuestionNumeric")
public class QuestionNumeric extends Question {

    private static final long serialVersionUID = 1L;

    @Field
    private SolutionNumeric solution;

    public SolutionNumeric getSolution() {
        return solution;
    }

    public void setSolution(SolutionNumeric solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "QuestionNumeric{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            // ", type=" + getType() +
            ", question='" + getQuestion() + '\'' +
            ", quizIds=" + getQuizIds() +
            ", solution=" + solution +
            '}';
    }
}

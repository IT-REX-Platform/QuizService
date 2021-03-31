package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

/**
 * A Question.
 */
@Document(collection = "questions")
@TypeAlias("QuestionSingleChoice")
public class QuestionSingleChoice extends Question {

    private static final long serialVersionUID = 1L;

    /**
     * Available choices.
     */
    @Field
    private Map<Integer, String> choices;

    /**
     * The key to the correct choice.
     */
    @Field
    private Integer solution;

    public Map<Integer, String> getChoices() {
        return choices;
    }

    public void setChoices(Map<Integer, String> choices) {
        this.choices = choices;
    }

    public Integer getSolution() {
        return solution;
    }

    public void setSolution(Integer solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "QuestionSingleChoice{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", question='" + getQuestionText() + '\'' +
            ", quizIds=" + getQuizIds() +
            ", choices=" + choices +
            ", solution='" + solution + '\'' +
            '}';
    }
}

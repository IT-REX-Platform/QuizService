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

    @Field
    private Map<String, String> choices;

    @Field
    private String solution;

    public Map<String, String> getChoices() {
        return choices;
    }

    public void setChoices(Map<String, String> choices) {
        this.choices = choices;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "QuestionSingleChoice{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", question='" + getQuestion() + '\'' +
            ", quizIds=" + getQuizIds() +
            ", choices=" + choices +
            ", solution='" + solution + '\'' +
            '}';
    }
}

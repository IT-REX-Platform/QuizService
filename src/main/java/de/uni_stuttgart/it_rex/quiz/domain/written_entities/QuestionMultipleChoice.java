package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

/**
 * A Question.
 */
@Document(collection = "questions")
@TypeAlias("QuestionMultipleChoice")
public class QuestionMultipleChoice extends Question {

    private static final long serialVersionUID = 1L;

    @Field
    private Map<String, String> choices;

    @Field
    private Map<String, Boolean> solution;

    public Map<String, String> getChoices() {
        return choices;
    }

    public void setChoices(Map<String, String> choices) {
        this.choices = choices;
    }

    public Map<String, Boolean> getSolution() {
        return solution;
    }

    public void setSolution(Map<String, Boolean> solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "QuestionMultipleChoice{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", quizIds=" + getQuizIds() +
            ", question='" + getQuestionText() + '\'' +
            ", choices=" + choices +
            ", solution=" + solution +
            '}';
    }
}

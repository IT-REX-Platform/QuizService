package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.util.Map;

public class QuestionSingleChoiceDTO extends QuestionDTO {

    private Map<String, String> choices;

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
        return "QuestionSingleChoiceDTO{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", quizIds=" + getQuizIds() +
            ", question='" + getQuestion() + '\'' +
            ", choices=" + choices +
            ", solution='" + solution + '\'' +
            '}';
    }

}

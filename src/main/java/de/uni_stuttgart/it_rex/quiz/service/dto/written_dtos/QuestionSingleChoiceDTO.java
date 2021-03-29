package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.util.Map;

public class QuestionSingleChoiceDTO extends QuestionDTO {

    private Map<Integer, String> choices;

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

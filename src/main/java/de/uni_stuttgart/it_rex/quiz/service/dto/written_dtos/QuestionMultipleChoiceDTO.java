package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.util.Map;

public class QuestionMultipleChoiceDTO extends QuestionDTO {

    private Map<String, String> choices;

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
        return "QuestionMultipleChoiceDTO{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", quizIds=" + getQuizIds() +
            // ", type=" + getType() +
            ", question='" + getQuestion() + '\'' +
            ", choices=" + choices +
            ", solution='" + solution + '\'' +
            '}';
    }

}

package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.util.Map;

public class QuestionMultipleChoiceDTO extends QuestionDTO {
    /**
     * Available choices.
     */
    private Map<Integer, String> choices;

    /**
     * Contains whether choices are right or wrong.
     */
    private Map<Integer, Boolean> solution;

    public Map<Integer, String> getChoices() {
        return choices;
    }

    public void setChoices(Map<Integer, String> choices) {
        this.choices = choices;
    }

    public Map<Integer, Boolean> getSolution() {
        return solution;
    }

    public void setSolution(Map<Integer, Boolean> solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "QuestionMultipleChoiceDTO{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", quizIds=" + getQuizIds() +
            ", question='" + getQuestion() + '\'' +
            ", choices=" + choices +
            ", solution='" + solution + '\'' +
            '}';
    }

}

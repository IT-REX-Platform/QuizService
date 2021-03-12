package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import de.uni_stuttgart.it_rex.quiz.domain.enumeration.QUESTIONTYPE;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class QuestionDTO implements Serializable {
    /**
     * Identifier.
     */
    private UUID id;

    /**
     * Course id.
     */
    @NotNull
    private UUID courseId;

    private List<UUID> quizIds;

    private QUESTIONTYPE type;

    private String question;

    private Map<String, String> choices;

    private String solution;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public List<UUID> getQuizIds() {
        return quizIds;
    }

    public void setQuizIds(List<UUID> quizIds) {
        this.quizIds = quizIds;
    }

    public QUESTIONTYPE getType() {
        return type;
    }

    public void setType(QUESTIONTYPE type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCourseId(), that.getCourseId()) && Objects.equals(getQuizIds(), that.getQuizIds()) && getType() == that.getType() && Objects.equals(getQuestion(), that.getQuestion()) && Objects.equals(getChoices(), that.getChoices()) && Objects.equals(getSolution(), that.getSolution());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseId(), getQuizIds(), getType(), getQuestion(), getChoices(), getSolution());
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + id +
            ", courseId=" + courseId +
            ", quizIds=" + quizIds +
            ", type=" + type +
            ", question='" + question + '\'' +
            ", choices=" + choices +
            ", solution='" + solution + '\'' +
            '}';
    }

// for later impls
    // for search
    // private List<String> tags;
}

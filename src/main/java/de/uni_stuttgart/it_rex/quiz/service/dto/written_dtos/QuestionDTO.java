package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "type")
@JsonSubTypes({ 
  @JsonSubTypes.Type(value = QuestionSingleChoiceDTO.class, name = "SINGLE_CHOICE"),
  @JsonSubTypes.Type(value = QuestionMultipleChoiceDTO.class, name = "MULTIPLE_CHOICE"),
  @JsonSubTypes.Type(value = QuestionNumericDTO.class, name = "NUMERIC")
})
public class QuestionDTO implements Serializable {
    /**
     * Identifier.
     */
    private UUID id;

    /**
     * Course id.
     */
    private UUID courseId;

    /**
     * Set of ids of quizzes in which the question is contained.
     */
    private Set<UUID> quizIds;

    /**
     * The question text.
     */
    private String question;

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

    public Set<UUID> getQuizIds() {
        return quizIds;
    }

    public void setQuizIds(Set<UUID> quizIds) {
        this.quizIds = quizIds;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + id +
            ", courseId=" + courseId +
            ", quizIds=" + quizIds +
            ", question='" + question + '\'' +
            '}';
    }

}

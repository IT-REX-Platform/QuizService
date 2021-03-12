package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.io.Serializable;
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
  private UUID courseId;

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
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCourseId(), that.getCourseId())
            && Objects.equals(getQuestion(), that.getQuestion());
      }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseId(), getQuestion());
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
            "id=" + id +
            ", courseId=" + courseId +
            ", question='" + question + '\'' +
            '}';
    }

    // for later impls
    // for search
    // private List<String> tags;
}

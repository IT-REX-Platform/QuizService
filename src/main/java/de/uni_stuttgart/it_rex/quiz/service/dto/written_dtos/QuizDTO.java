package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class QuizDTO implements Serializable {
  /**
   * Identifier.
   */
  private UUID id;

  /**
   * Title of the chapter.
   */
  private String name;

  /**
   * Course id.
   */
  private UUID courseId;

  /**
   * List of questions
   */
  private List<QuestionDTO> questions;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizDTO quizDTO = (QuizDTO) o;
        return Objects.equals(getId(), quizDTO.getId()) && Objects.equals(getName(), quizDTO.getName()) && Objects.equals(getCourseId(), quizDTO.getCourseId()) && Objects.equals(getQuestions(), quizDTO.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCourseId(), getQuestions());
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", courseId=" + courseId +
            ", questions=" + questions +
            '}';
    }
}

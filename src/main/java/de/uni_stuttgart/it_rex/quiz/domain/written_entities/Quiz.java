package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.*;

/**
 * A Quiz.
 */
@Document(collection = "quizzes")
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Field
    private UUID courseId;

    @Field
    private String name;


    @DBRef(lazy = true)
    @Field
    private Set<Question> questions = new HashSet<>();

    public boolean isNew() {
        return (getId() == null);
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Quiz addQuestion(Question question) {
        this.questions.add(question);
        question.getQuizzes().add(this);
        return this;
    }

    public Quiz removeQuestion(Question question) {
        this.questions.remove(question);
        question.getQuizzes().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(getId(), quiz.getId()) && Objects.equals(getName(), quiz.getName())
                && Objects.equals(getCourseId(), quiz.getCourseId())
                && Objects.equals(getQuestions(), quiz.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCourseId(), getQuestions());
    }

    @Override
    public String toString() {
        return "Quiz{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", courseId=" + courseId +
            ", questions=" + questions +
            '}';
    }
}

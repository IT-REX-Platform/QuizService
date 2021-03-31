package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * A Quiz.
 */
@Document(collection = "quizzes")
@TypeAlias("Quiz")
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The primary key.
     */
    @Id
    private UUID id;

    /**
     * The Course Id.
     */
    @Field
    private UUID courseId;

    /**
     * The Quiz name.
     */
    @Field
    private String name;

    /**
     * List of to the contained questions.
     */
    @DBRef(lazy = true)
    @Field
    private List<Question> questions = new LinkedList<>();

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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Quiz addQuestion(Question question) {
        this.questions.add(question);
        question.getQuizIds().add(this.getId());
        return this;
    }

    public Quiz addQuestions(List<Question> questions) {
        this.questions.addAll(questions);
        questions.forEach(o -> o.getQuizIds().add(this.getId()));
        return this;
    }

    public Quiz removeQuestion(Question question) {
        this.questions.remove(question);
        question.getQuizIds().remove(this.getId());
        return this;
    }

    public Quiz removeQuestions(List<Question> questions) {
        this.questions.removeAll(questions);
        questions.forEach(o -> o.getQuizIds().remove(this.getId()));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(getId(), quiz.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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

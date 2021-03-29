package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * A Question.
 */
@Document(collection = "questions")
@TypeAlias("Question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Field
    private UUID courseId;

    @Field
    private String questionText;

    @Field
    private Set<UUID> quizIds = new HashSet<>();

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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Set<UUID> getQuizIds() {
        return quizIds;
    }

    public void setQuizIds(Set<UUID> quizIds) {
        this.quizIds = quizIds;
    }

    public Question addQuizId(UUID quizId) {
        this.quizIds.add(quizId);
        return this;
    }

    public Question addQuizIds(Set<UUID> quizIds) {
        this.quizIds.addAll(quizIds);
        return this;
    }

    public Question removeQuizId(UUID quizId) {
        this.quizIds.remove(quizId);
        return this;
    }

    public Question removeQuizIds(Set<UUID> quizIds) {
        this.quizIds.removeAll(quizIds);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(getId(), question1.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", question=" + questionText +
            ", quizzes=" + quizIds +
            '}';
    }
}

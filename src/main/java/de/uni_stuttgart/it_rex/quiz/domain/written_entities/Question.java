package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import de.uni_stuttgart.it_rex.quiz.domain.enumeration.QUESTIONTYPE;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * A Question.
 */
@Document(collection = "questions")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Field
    private UUID courseId;

    @Field
    private QUESTIONTYPE type;

    @Field
    private String question;

    @Field
    private Map<String, String> choices;

    @Field
    private String solution;

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

    public Set<UUID> getQuizIds() {
        return quizIds;
    }

    public void setQuizIds(Set<UUID> quizIds) {
        this.quizIds = quizIds;
    }

    public Question addQuiz(Quiz quiz) {
        this.quizIds.add(quiz.getId());
        quiz.getQuestions().add(this);
        return this;
    }

    public Question addQuizzes(Set<Quiz> quizzes) {
        this.quizIds.addAll(quizzes.stream().map(Quiz::getId).collect(Collectors.toList()));
        quizzes.forEach(o -> o.getQuestions().add(this));
        return this;
    }

    public Question removeQuiz(Quiz quiz) {
        this.quizIds.remove(quiz.getId());
        quiz.getQuestions().remove(this);
        return this;
    }

    public Question removeQuizzes(Set<Quiz> quizzes) {
        this.quizIds.removeAll(quizzes.stream().map(Quiz::getId).collect(Collectors.toList()));
        quizzes.forEach(o -> o.getQuestions().add(this));
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
            ", type=" + type +
            ", question='" + question + '\'' +
            ", choices=" + choices +
            ", solution='" + solution + '\'' +
            ", quizzes=" + quizIds +
            '}';
    }
}

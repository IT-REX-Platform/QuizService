package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import de.uni_stuttgart.it_rex.quiz.domain.enumeration.QUESTIONTYPE;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.AnswerTypeDTO.ANSWER_TYPE;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.*;

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

    @DBRef(lazy = true)
    @Field
    private Set<Quiz> quizzes = new HashSet<>();

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

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public Question addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
        quiz.getQuestions().add(this);
        return this;
    }

    public Question addQuizzes(Set<Quiz> quizzes) {
        this.quizzes.addAll(quizzes);
        quizzes.stream().map(Quiz::getQuestions).forEach(o -> o.add(this));
        return this;
    }

    public Question removeQuiz(Quiz quiz) {
        this.quizzes.remove(quiz);
        quiz.getQuestions().remove(this);
        return this;
    }

    public Question removeQuizzes(Set<Quiz> quizzes) {
        this.quizzes.removeAll(quizzes);
        quizzes.stream().map(Quiz::getQuestions).forEach(o -> o.remove(this));
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
            ", quizzes=" + quizzes +
            '}';
    }
}

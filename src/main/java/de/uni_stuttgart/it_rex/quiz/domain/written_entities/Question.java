package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.AnswerTypeDTO.ANSWER_TYPE;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * A Question.
 */
@Document(collection = "questions")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Field("type")
    private ANSWER_TYPE type;

    @Field("question")
    private String question;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isNew() {
        return (getId() == null);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ANSWER_TYPE getType() {
        return type;
    }

    public void setType(ANSWER_TYPE type) {
        this.type = type;
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
        Question question1 = (Question) o;
        return Objects.equals(getId(), question1.getId()) && getType() == question1.getType() && Objects.equals(getQuestion(), question1.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getQuestion());
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", type=" + type +
            ", question='" + question + '\'' +
            '}';
    }
}

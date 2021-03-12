package de.uni_stuttgart.it_rex.quiz.domain.written_entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A Quiz.
 */
@Document(collection = "quiz")
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Field("content")
    private String content;

    // @DBRef
    // @Field("b")
    // private Set<EntityB> bs = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isNew() {
        return (getId() == null);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // public Set<EntityB> getBs() {
    //     return bs;
    // }

    // public EntityA bs(Set<EntityB> entityBS) {
    //     this.bs = entityBS;
    //     return this;
    // }

    // public EntityA addB(EntityB entityB) {
    //     this.bs.add(entityB);
    //     entityB.setA(this);
    //     return this;
    // }

    // public EntityA removeB(EntityB entityB) {
    //     this.bs.remove(entityB);
    //     entityB.setA(null);
    //     return this;
    // }

    // public void setBs(Set<EntityB> entityBS) {
    //     this.bs = entityBS;
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quiz)) {
            return false;
        }
        return id != null && id.equals(((Quiz) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Quiz{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            "}";
    }
}

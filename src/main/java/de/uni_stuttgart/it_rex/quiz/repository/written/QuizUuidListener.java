package de.uni_stuttgart.it_rex.quiz.repository.written;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;

@Component
public class QuizUuidListener extends AbstractMongoEventListener<Quiz> {
 
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Quiz> event) {
        Quiz quiz = event.getSource();
        if (quiz.isNew()) {
            quiz.setId(UUID.randomUUID());
        }
    }
}

package de.uni_stuttgart.it_rex.quiz.repository.written;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Question;

/**
 * Hook for UUID generation before save.
 */
@Component
public class QuestionUuidListener extends AbstractMongoEventListener<Question> {
 
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Question> event) {
        Question quiz = event.getSource();
        if (quiz.isNew()) {
            quiz.setId(UUID.randomUUID());
        }
    }
}

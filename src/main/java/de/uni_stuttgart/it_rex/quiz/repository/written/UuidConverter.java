package de.uni_stuttgart.it_rex.quiz.repository.written;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;

// @Component
// public class UuidConverter implements Converter<User, DBObject> {
//     @Override
//     public DBObject convert(User user) {
//         DBObject dbObject = new BasicDBObject();
//         dbObject.put("name", user.getName());
//         dbObject.put("age", user.getAge());
//         if (user.getEmailAddress() != null) {
//             DBObject emailDbObject = new BasicDBObject();
//             emailDbObject.put("value", user.getEmailAddress().getValue());
//             dbObject.put("email", emailDbObject);
//         }
//         dbObject.removeField("_class");
//         return dbObject;
//     }
// }
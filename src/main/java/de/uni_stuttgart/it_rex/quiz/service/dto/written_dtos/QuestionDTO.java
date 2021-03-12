package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class QuestionDTO implements Serializable {
  /**
   * Identifier.
   */
  private UUID id;

  /**
   * Course id.
   */
  private UUID courseId;

  private String question;

  private AnswerTypeDTO answer;

  // for later impls
  // for search
  // private List<String> tags;
}

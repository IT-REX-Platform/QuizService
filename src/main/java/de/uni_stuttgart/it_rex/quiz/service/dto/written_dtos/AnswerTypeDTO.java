package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



public class AnswerTypeDTO implements Serializable{

  public enum ANSWER_TYPE {
    SINGLE_CHOICE,
    MULTIPLE_CHOICE,
    NUMERIC
  };

  /**
   * Identifier.
   */
  private UUID id;

  private ANSWER_TYPE type;

}

// public class ChoiceAnswer {
//   private String content;
//   private Boolean correct;
// }

// public class NumericAnswer {
//   private Float content;
//   private Float epsilon;
// }

// public class ChoiceAnswerTypeDTO extends AnswerTypeDTO {
//   private List<ChoiceAnswer> answer;
// }

// public class NumericAnswerTypeDTO extends AnswerTypeDTO {
//   private NumericAnswer answer;
// }




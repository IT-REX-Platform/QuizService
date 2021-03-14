package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;


public class QuestionNumericDTO extends QuestionDTO {

    private SolutionNumericDTO solution;

    public SolutionNumericDTO getSolution() {
        return solution;
    }

    public void setSolution(SolutionNumericDTO solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "QuestionNumericDTO{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", quizIds=" + getQuizIds() +
            // ", type=" + getType() +
            ", question='" + getQuestion() + '\'' +
            ", solution=" + solution +
            '}';
    }

}

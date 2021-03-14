package de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos;

import java.io.Serializable;

public class SolutionNumericDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private float result;
    private float epsilon;

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public float getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(float epsilon) {
        this.epsilon = epsilon;
    }

    @Override
    public String toString() {
        return "Solution{" +
            "result=" + result +
            ", epsilon=" + epsilon +
            '}';
    }
};
package com.example.jpa.quiz.dto;

public class QuizObjectiveDTO extends QuizDTO {
    protected int answer;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}

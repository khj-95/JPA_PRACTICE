package com.example.jpa.quiz.dto;

public class QuizDescriptiveDTO extends QuizDTO {
    protected String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

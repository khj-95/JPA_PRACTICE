package com.example.jpa.quiz.vo;

public class QuizVO {
    private Long quizId;
    private String question;
    private String content;
    private String qType;

    public QuizVO(Long quizId, String question, String content, String qType) {
        this.quizId = quizId;
        this.question = question;
        this.content = content;
        this.qType = qType;
    }

    public String getqType() {
        return qType;
    }

    public String getContent() {
        return content;
    }

    public String getQuestion() {
        return question;
    }

    public Long getQuizId() {
        return quizId;
    }
}

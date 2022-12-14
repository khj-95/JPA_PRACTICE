package com.example.jpa.quiz.dto;

import com.example.jpa.quiz.constant.*;

public abstract class QuizDTO {
    protected String question;
    protected String content;
    protected QType qType;
    protected Category category;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QType getQType() {
        return qType;
    }

    public void setQType(QType qType) {
        this.qType = qType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

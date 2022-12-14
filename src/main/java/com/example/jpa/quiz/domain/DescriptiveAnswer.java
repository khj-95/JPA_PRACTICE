package com.example.jpa.quiz.domain;

import javax.persistence.*;

@Entity
public class DescriptiveAnswer {
    @Id
    @Column(name = "DESCRIPTIVE_ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answer;
    @OneToOne
    private Quiz quiz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}

package com.example.jpa.quiz.domain;

import javax.persistence.*;

@Entity
public class ObjectiveAnswer {
    @Id
    @Column(name = "OBJECTIVE_ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int answer;
    @OneToOne
    private Quiz quiz;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}

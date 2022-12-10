package com.example.jpa.quiz.domain;

import javax.persistence.*;

@Entity
public class QuizQtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "QTYPE_ID")
    private Qtype qtype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Qtype getQtype() {
        return qtype;
    }

    public void setQtype(Qtype qtype) {
        this.qtype = qtype;
    }
}

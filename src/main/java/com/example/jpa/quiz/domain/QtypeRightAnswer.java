package com.example.jpa.quiz.domain;

import javax.persistence.*;

@Entity
public class QtypeRightAnswer {
    @Id
    @Column(name = "QTYPE_RIGHT_ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private RightAnswer rightAnswer;
    @ManyToOne
    @JoinColumn(name = "QTYPE_ID")
    private Qtype qtype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RightAnswer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(RightAnswer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Qtype getQtype() {
        return qtype;
    }

    public void setQtype(Qtype qtype) {
        this.qtype = qtype;
    }
}

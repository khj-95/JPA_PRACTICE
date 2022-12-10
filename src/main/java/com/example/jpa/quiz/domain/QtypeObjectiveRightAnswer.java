package com.example.jpa.quiz.domain;

import javax.persistence.*;
@Entity
public class QtypeObjectiveRightAnswer {
    @Id
    @Column(name = "QTYPE_OBJECTIVE_RIGHT_ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private ObjectiveRightAnswer objectiveRightAnswer;
    @ManyToOne
    @JoinColumn(name = "QTYPE_ID")
    private Qtype qtype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ObjectiveRightAnswer getObjectiveRightAnswer() {
        return objectiveRightAnswer;
    }

    public void setObjectiveRightAnswer(ObjectiveRightAnswer objectiveRightAnswer) {
        this.objectiveRightAnswer = objectiveRightAnswer;
    }

    public Qtype getQtype() {
        return qtype;
    }

    public void setQtype(Qtype qtype) {
        this.qtype = qtype;
    }
}

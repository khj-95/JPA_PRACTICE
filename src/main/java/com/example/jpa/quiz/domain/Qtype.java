package com.example.jpa.quiz.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Qtype {
    @Id
    @Column(name = "QTYPE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String qtype;
    @OneToMany(mappedBy = "qtype")
    private List<QuizQtype> quizQtypes;
    @OneToMany(mappedBy = "qtype")
    private List<QtypeObjectiveRightAnswer> qtypeObjectiveRightAnswers;
    @OneToMany(mappedBy = "qtype")
    private List<QtypeRightAnswer> qtypeRightAnswers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getqQtype() {
        return qtype;
    }

    public void setqQtype(String qtype) {
        this.qtype = qtype;
    }

    public List<QuizQtype> getQuizQtypes() {
        return quizQtypes;
    }

    public void setQuizQtypes(List<QuizQtype> quizQtypes) {
        this.quizQtypes = quizQtypes;
    }

    public List<QtypeObjectiveRightAnswer> getQtypeObjectiveRightAnswers() {
        return qtypeObjectiveRightAnswers;
    }

    public void setQtypeObjectiveRightAnswers(List<QtypeObjectiveRightAnswer> qtypeObjectiveRightAnswers) {
        this.qtypeObjectiveRightAnswers = qtypeObjectiveRightAnswers;
    }

    public List<QtypeRightAnswer> getQtypeRightAnswers() {
        return qtypeRightAnswers;
    }

    public void setQtypeRightAnswers(List<QtypeRightAnswer> qtypeRightAnswers) {
        this.qtypeRightAnswers = qtypeRightAnswers;
    }
}

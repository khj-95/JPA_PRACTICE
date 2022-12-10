package com.example.jpa.quiz.domain;

import javax.persistence.*;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private String content;
    @OneToOne
    private QuizQtype quizQType;
    @OneToOne
    @JoinColumn(name = "QUIZ_CATEGORY_ID")
    private QuizCategory quizCategory;
    @OneToOne
    @JoinColumn(name = "OBJECTIVE_RIGHT_ANSWER_ID")
    private ObjectiveRightAnswer objectiveRightAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public QuizQtype getQuizQType() {
        return quizQType;
    }

    public void setQuizQType(QuizQtype quizQType) {
        this.quizQType = quizQType;
    }

    public QuizCategory getQuizCategory() {
        return quizCategory;
    }

    public void setQuizCategory(QuizCategory quizCategory) {
        this.quizCategory = quizCategory;
    }

    public ObjectiveRightAnswer getObjectiveRightAnswer() {
        return objectiveRightAnswer;
    }

    public void setObjectiveRightAnswer(ObjectiveRightAnswer objectiveRightAnswer) {
        this.objectiveRightAnswer = objectiveRightAnswer;
    }
}

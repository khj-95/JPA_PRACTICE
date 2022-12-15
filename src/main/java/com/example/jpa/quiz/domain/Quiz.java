package com.example.jpa.quiz.domain;

import com.example.jpa.quiz.constant.Category;

import javax.persistence.*;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private String content;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "OBJECTIVE_ANSWER_ID")
    private ObjectiveAnswer objectiveAnswer;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ObjectiveAnswer getObjectiveAnswer() {
        return objectiveAnswer;
    }

    public void setObjectiveAnswer(ObjectiveAnswer objectiveAnswer) {
        this.objectiveAnswer = objectiveAnswer;
    }

    public DescriptiveAnswer getDescriptiveAnswer() {
        return descriptiveAnswer;
    }

    public void setDescriptiveAnswer(DescriptiveAnswer descriptiveAnswer) {
        this.descriptiveAnswer = descriptiveAnswer;
    }

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "DESCRIPTIVE_ANSWER_ID")
    private DescriptiveAnswer descriptiveAnswer;
}

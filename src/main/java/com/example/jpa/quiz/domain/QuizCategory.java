package com.example.jpa.quiz.domain;

import javax.persistence.*;
@Entity
public class QuizCategory {
    @Id
    @Column(name = "QUIZ_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "quizCategory")
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

package com.example.jpa.quiz.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<QuizCategory> quizCategories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuizCategory> getQuizCategories() {
        return quizCategories;
    }

    public void setQuizCategories(List<QuizCategory> quizCategories) {
        this.quizCategories = quizCategories;
    }
}

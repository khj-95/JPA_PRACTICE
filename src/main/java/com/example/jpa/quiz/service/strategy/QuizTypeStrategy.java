package com.example.jpa.quiz.service.strategy;

import com.example.jpa.quiz.domain.Quiz;
import com.example.jpa.quiz.dto.QuizDTO;

public interface QuizTypeStrategy {
    void add(QuizDTO dto);

    QuizDTO toQuizDTO(Quiz quiz);

    QuizDTO update(Quiz quiz, QuizDTO dto);

    void delete(Quiz quiz);

}

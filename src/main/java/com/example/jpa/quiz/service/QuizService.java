package com.example.jpa.quiz.service;

import com.example.jpa.quiz.domain.Quiz;
import com.example.jpa.quiz.dto.QuizDTO;
import com.example.jpa.quiz.exception.*;
import com.example.jpa.quiz.repository.QuizRepository;
import com.example.jpa.quiz.service.strategy.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class QuizService {
    private final QuizRepository repository;
    private final QuizDescriptiveService descriptiveService;
    private final QuizObjectiveService objectiveService;

    public QuizService(QuizRepository repository, QuizDescriptiveService descriptiveService, QuizObjectiveService objectiveService) {
        this.repository = repository;
        this.descriptiveService = descriptiveService;
        this.objectiveService = objectiveService;
    }

    public void add(QuizDTO dto) {
        if (Objects.isNull(dto)) {
            throw new NullPointerException("QuizDTO is empty");
        }
        if (Objects.isNull(dto.getQType())) {
            throw new InvalidQuizTypeException();
        }
        if (Objects.isNull(dto.getCategory())) {
            throw new InvalidCategoryException();
        }

        switch (dto.getQType()) {
            case DESCRIPTIVE:
                descriptiveService.add(dto);
                break;
            case OBJECTIVE:
                objectiveService.add(dto);
                break;
            default:
                return;
        }
    }

    @Transactional
    public QuizDTO retrieve(Long id) {
        Quiz quiz = repository.findById(id).orElseThrow(() -> new InvalidQuizInstanceException());
        if (Objects.nonNull(quiz.getDescriptiveAnswer())) {
            return descriptiveService.toQuizDTO(quiz);
        }
        if (Objects.nonNull(quiz.getObjectiveAnswer())) {
            return objectiveService.toQuizDTO(quiz);
        }
        return null;
    }

    public QuizDTO update(Long id, QuizDTO dto) {
        if (Objects.isNull(dto)) {
            throw new NullPointerException("QuizDTO is empty");
        }

        Quiz quiz = repository.findById(id).orElseThrow(() -> new InvalidQuizInstanceException());

        if (Objects.nonNull(quiz.getDescriptiveAnswer())) {
            return descriptiveService.update(quiz, dto);
        }
        if (Objects.nonNull(quiz.getObjectiveAnswer())) {
            return objectiveService.update(quiz, dto);
        }
        return null;
    }
}

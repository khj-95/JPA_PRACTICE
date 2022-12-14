package com.example.jpa.quiz.service;

import com.example.jpa.quiz.dto.QuizDTO;
import com.example.jpa.quiz.exception.*;
import com.example.jpa.quiz.service.strategy.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class QuizService {

    private final QuizDescriptiveService descriptiveService;
    private final QuizObjectiveService objectiveService;

    public QuizService(QuizDescriptiveService descriptiveService, QuizObjectiveService objectiveService) {
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
}

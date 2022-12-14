package com.example.jpa.quiz.service.strategy;

import com.example.jpa.quiz.contant.*;
import com.example.jpa.quiz.dto.*;
import com.example.jpa.quiz.exception.*;
import com.example.jpa.quiz.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuizObjectiveServiceTests {
    @Mock
    QuizRepository quizRepository;
    @Mock
    ObjectiveAnswerRepository objectiveAnswerRepository;
    @InjectMocks
    QuizObjectiveService service;

    @Test
    void test() {
        QuizObjectiveDTO dto = new QuizObjectiveDTO();
        dto.setQType(QType.OBJECTIVE);
        dto.setQuestion("Test question");
        dto.setCategory(Category.JAVA);
        dto.setAnswer(1);
        service.add(dto);
    }

    @Test
    void test_invalid_objective_quiz_instance() {
        try {
            QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
            service.add(dto);
        } catch (InvalidObjectiveInstanceException exception) {
            return;
        }

        fail("걸러내지 못함");
    }

    @Test
    void test_invalid_quiz_instance() {
        try {
            when(quizRepository.save(ArgumentMatchers.any())).thenThrow(new RuntimeException());
            QuizObjectiveDTO dto = new QuizObjectiveDTO();
            service.add(dto);
        } catch (InvalidQuizInstanceException exception) {
            return;
        }

        fail("걸러내지 못함");
    }

    @Test
    void test_invalid_objective_answer_instance() {
        try {
            when(objectiveAnswerRepository.save(ArgumentMatchers.any())).thenThrow(new RuntimeException());
            QuizObjectiveDTO dto = new QuizObjectiveDTO();
            service.add(dto);
        } catch (InvalidObjectiveAnswerInstanceException exception) {
            return;
        }

        fail("걸러내지 못함");
    }
}

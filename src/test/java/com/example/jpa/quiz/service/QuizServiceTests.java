package com.example.jpa.quiz.service;

import com.example.jpa.quiz.contant.*;
import com.example.jpa.quiz.dto.*;
import com.example.jpa.quiz.exception.*;
import com.example.jpa.quiz.service.strategy.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTests {

    @Mock
    QuizDescriptiveService descriptiveService;
    @Mock
    QuizObjectiveService objectiveService;

    @InjectMocks
    QuizService service;

    @Test
    void add_descriptive_quiz() {
        QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
        dto.setQType(QType.DESCRIPTIVE);
        dto.setQuestion("Test question");
        dto.setCategory(Category.JAVA);
        dto.setAnswer("answer");

        service.add(dto);
    }

    @Test
    void add_objective_quiz() {
        QuizObjectiveDTO dto = new QuizObjectiveDTO();
        dto.setQType(QType.DESCRIPTIVE);
        dto.setQuestion("Test question");
        dto.setCategory(Category.JAVA);
        dto.setAnswer(1);

        service.add(dto);
    }

    @Test
    void when_dto_is_null() {
        try {
            service.add(null);
        } catch (NullPointerException exception) {
            return;
        }

        fail("걸러내지 못함");
    }

    @Test
    void when_qtype_is_null() {
        QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
        dto.setQType(null);
        dto.setQuestion("Test question");
        dto.setCategory(Category.JAVA);
        dto.setAnswer("answer");

        try {
            service.add(dto);
        } catch (InvalidQuizTypeException exception) {
            return;
        }

        fail("걸러내지 못함");
    }

    @Test
    void when_category_is_null() {
        QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
        dto.setQType(QType.OBJECTIVE);
        dto.setQuestion("Test question");
        dto.setCategory(null);
        dto.setAnswer("answer");

        try {
            service.add(dto);
        } catch (InvalidCategoryException exception) {
            return;
        }

        fail("걸러내지 못함");
    }
}

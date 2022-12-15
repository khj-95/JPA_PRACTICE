package com.example.jpa.quiz.service;

import com.example.jpa.quiz.constant.*;
import com.example.jpa.quiz.domain.*;
import com.example.jpa.quiz.dto.*;
import com.example.jpa.quiz.exception.*;
import com.example.jpa.quiz.repository.QuizRepository;
import com.example.jpa.quiz.service.strategy.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTests {

    @Mock
    QuizDescriptiveService descriptiveService;
    @Mock
    QuizObjectiveService objectiveService;
    @Mock
    QuizRepository quizRepository;
    @Mock
    Quiz quiz;

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

    @Test
    void retrieve_quiz_by_id() {
        Quiz quiz = new Quiz();
        quiz.setId(1l);
        quiz.setQuestion("test Question");
        quiz.setContent("test Content");
        quiz.setCategory(Category.JAVA.name());
        quiz.setDescriptiveAnswer(new DescriptiveAnswer());

        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(quiz));
        service.retrieve(1l);
    }

    @Test
    void retrieve_descriptive_quiz_by_id() {
        Quiz quiz = new Quiz();
        quiz.setId(1l);
        quiz.setQuestion("test Question");
        quiz.setContent("test Content");
        quiz.setCategory(Category.JAVA.name());
        quiz.setDescriptiveAnswer(new DescriptiveAnswer());

        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(quiz));
        when(descriptiveService.toQuizDTO(ArgumentMatchers.any())).thenReturn(new QuizDescriptiveDTO());
        assertThat(service.retrieve(1l)).isInstanceOf(QuizDTO.class);
    }

    @Test
    void retrieve_objective_quiz_by_id() {
        Quiz quiz = new Quiz();
        quiz.setId(1l);
        quiz.setQuestion("test Question");
        quiz.setContent("test Content");
        quiz.setCategory(Category.JAVA.name());
        quiz.setObjectiveAnswer(new ObjectiveAnswer());

        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(quiz));
        when(objectiveService.toQuizDTO(ArgumentMatchers.any())).thenReturn(new QuizObjectiveDTO());
        assertThat(service.retrieve(1l)).isInstanceOf(QuizDTO.class);
    }

    @Test
    void retrieve_quiz_by_id_is_null() {
        try {
            when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
            service.retrieve(1l);
        } catch (InvalidQuizInstanceException exception) {
            return;
        }

        fail("걸러내지 못함");
    }


}

package com.example.jpa.quiz.service.strategy;

import com.example.jpa.quiz.contant.*;
import com.example.jpa.quiz.domain.*;
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
public class QuizDescriptiveServiceTests {
    @Mock
    QuizRepository quizRepository;
    @Mock
    DescriptiveAnswerRepository descriptiveAnswerRepository;
    @InjectMocks
    QuizDescriptiveService service;

    @Test
    void test() {
        QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
        dto.setQType(QType.DESCRIPTIVE);
        dto.setQuestion("Test question");
        dto.setCategory(Category.JAVA);
        dto.setAnswer("answer");
        service.add(dto);
    }

    @Test
    void test_invalid_descriptive_quiz_instance() {
        try {
            QuizObjectiveDTO dto = new QuizObjectiveDTO();
            service.add(dto);
        } catch (InvalidDescriptiveInstanceException exception) {
            return;
        }

        fail("걸러내지 못함");
    }

    @Test
    void test_invalid_quiz_instance() {
        try {
            when(quizRepository.save(ArgumentMatchers.any())).thenThrow(new RuntimeException());
            QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
            service.add(dto);
        } catch (InvalidQuizInstanceException exception) {
            return;
        }

        fail("걸러내지 못함");
    }

    @Test
    void test_invalid_descriptive_answer_instance() {
        try {
            when(descriptiveAnswerRepository.save(ArgumentMatchers.any())).thenThrow(new RuntimeException());
            QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
            service.add(dto);
        } catch (InvalidDescriptiveAnswerInstanceException exception) {
            return;
        }

        fail("걸러내지 못함");
    }

    @Test
    void test_to_quiz_dto() {
        Quiz quiz = new Quiz();
        quiz.setId(1l);
        quiz.setQuestion("test Question");
        quiz.setContent("test Content");
        quiz.setCategory(Category.JAVA.name());

        DescriptiveAnswer descriptiveAnswer = new DescriptiveAnswer();
        descriptiveAnswer.setId(1l);
        descriptiveAnswer.setAnswer("test answer");
        descriptiveAnswer.setQuiz(quiz);

        quiz.setDescriptiveAnswer(descriptiveAnswer);

        service.toQuizDTO(quiz);
    }
}

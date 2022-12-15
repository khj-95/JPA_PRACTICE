package com.example.jpa.quiz.service.strategy;

import com.example.jpa.quiz.constant.*;
import com.example.jpa.quiz.domain.*;
import com.example.jpa.quiz.dto.*;
import com.example.jpa.quiz.exception.*;
import com.example.jpa.quiz.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        dto.setCategory(Category.JAVA);
        dto.setAnswer("answer");

        when(quizRepository.save(ArgumentMatchers.any())).thenReturn(new Quiz());
        when(descriptiveAnswerRepository.save(ArgumentMatchers.any())).thenReturn(new DescriptiveAnswer());

        service.add(dto);
    }

    @Test
    void test_invalid_descriptive_quiz_instance() {
        assertThatThrownBy(() -> service.add(new QuizObjectiveDTO())).isInstanceOf(InvalidDescriptiveInstanceException.class);
    }

    @Test
    void test_invalid_quiz_instance() {
        when(quizRepository.save(ArgumentMatchers.any())).thenThrow(new RuntimeException());
        assertThatThrownBy(() -> service.add(new QuizDescriptiveDTO())).isInstanceOf(InvalidQuizInstanceException.class);
    }

    @Test
    void test_invalid_descriptive_answer_instance() {
        QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
        dto.setAnswer("test answer");
        dto.setCategory(Category.JAVA);

        when(quizRepository.save(ArgumentMatchers.any())).thenReturn(new Quiz());
        when(descriptiveAnswerRepository.save(ArgumentMatchers.any())).thenThrow(new RuntimeException());

        assertThatThrownBy(() -> service.add(dto)).isInstanceOf(InvalidDescriptiveAnswerInstanceException.class);
    }

    @Test
    void test_to_quiz_dto() {
        Quiz quiz = new Quiz();
        quiz.setId(1l);
        quiz.setQuestion("test Question");
        quiz.setContent("test Content");
        quiz.setCategory(Category.JAVA);

        DescriptiveAnswer descriptiveAnswer = new DescriptiveAnswer();
        descriptiveAnswer.setId(1l);
        descriptiveAnswer.setAnswer("test answer");
        descriptiveAnswer.setQuiz(quiz);

        quiz.setDescriptiveAnswer(descriptiveAnswer);

        assertThat(service.toQuizDTO(quiz)).isInstanceOf(QuizDescriptiveDTO.class);
    }

    @Test
    void test_to_quiz_dto_when_answer_is_null() {
        Quiz quiz = new Quiz();
        quiz.setId(1l);
        quiz.setQuestion("test Question");
        quiz.setContent("test Content");
        quiz.setCategory(Category.JAVA);

        assertThat(service.toQuizDTO(quiz)).hasFieldOrPropertyWithValue("answer", null);
    }
}

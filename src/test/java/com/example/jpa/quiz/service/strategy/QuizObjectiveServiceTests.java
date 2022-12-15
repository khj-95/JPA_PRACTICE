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
        dto.setCategory(Category.JAVA);
        dto.setAnswer(1);

        when(quizRepository.save(ArgumentMatchers.any())).thenReturn(new Quiz());
        when(objectiveAnswerRepository.save(ArgumentMatchers.any())).thenReturn(new ObjectiveAnswer());

        service.add(dto);
    }

    @Test
    void test_invalid_objective_quiz_instance() {
        assertThatThrownBy(() -> service.add(new QuizDescriptiveDTO())).isInstanceOf(InvalidObjectiveInstanceException.class);
    }

    @Test
    void test_invalid_quiz_instance() {
        assertThatThrownBy(() -> service.add(new QuizObjectiveDTO())).isInstanceOf(InvalidQuizInstanceException.class);
    }

    @Test
    void test_invalid_objective_answer_instance() {
        QuizObjectiveDTO dto = new QuizObjectiveDTO();
        dto.setAnswer(1);
        dto.setCategory(Category.JAVA);

        when(quizRepository.save(ArgumentMatchers.any())).thenReturn(new Quiz());
        when(objectiveAnswerRepository.save(ArgumentMatchers.any())).thenThrow(new RuntimeException());

        assertThatThrownBy(() -> service.add(dto)).isInstanceOf(InvalidObjectiveAnswerInstanceException.class);
    }

    @Test
    void test_to_quiz_dto() {
        Quiz quiz = new Quiz();
        quiz.setId(1l);
        quiz.setQuestion("test Question");
        quiz.setContent("test Content");
        quiz.setCategory(Category.JAVA.name());

        ObjectiveAnswer objectiveAnswer = new ObjectiveAnswer();
        objectiveAnswer.setId(1l);
        objectiveAnswer.setAnswer(1);
        objectiveAnswer.setQuiz(quiz);

        quiz.setObjectiveAnswer(objectiveAnswer);

        assertThat(service.toQuizDTO(quiz)).isInstanceOf(QuizObjectiveDTO.class);
    }

    @Test
    void test_to_quiz_dto_when_answer_is_null() {
        Quiz quiz = new Quiz();
        quiz.setId(1l);
        quiz.setQuestion("test Question");
        quiz.setContent("test Content");
        quiz.setCategory(Category.JAVA.name());

        assertThat(service.toQuizDTO(quiz)).hasFieldOrPropertyWithValue("answer", null);
    }
}

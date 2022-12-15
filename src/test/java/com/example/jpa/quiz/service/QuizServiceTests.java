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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        assertThatThrownBy(() -> service.add(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void when_qtype_is_null() {
        QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
        dto.setQType(null);
        dto.setQuestion("Test question");
        dto.setCategory(Category.JAVA);
        dto.setAnswer("answer");

        assertThatThrownBy(() -> service.add(dto)).isInstanceOf(InvalidQuizTypeException.class);
    }

    @Test
    void when_category_is_null() {
        QuizDescriptiveDTO dto = new QuizDescriptiveDTO();
        dto.setQType(QType.OBJECTIVE);
        dto.setQuestion("Test question");
        dto.setCategory(null);
        dto.setAnswer("answer");

        assertThatThrownBy(() -> service.add(dto)).isInstanceOf(InvalidCategoryException.class);
    }

    @Test
    void retrieve_quiz_by_id() {
        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Quiz()));
        assertThat(service.retrieve(1l)).isNull();
    }

    @Test
    void retrieve_descriptive_quiz_by_id() {
        Quiz quiz = new Quiz();
        quiz.setDescriptiveAnswer(new DescriptiveAnswer());

        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(quiz));
        when(descriptiveService.toQuizDTO(ArgumentMatchers.any())).thenReturn(new QuizDescriptiveDTO());
        assertThat(service.retrieve(1l)).isInstanceOf(QuizDescriptiveDTO.class);
    }

    @Test
    void retrieve_objective_quiz_by_id() {
        Quiz quiz = new Quiz();
        quiz.setObjectiveAnswer(new ObjectiveAnswer());

        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(quiz));
        when(objectiveService.toQuizDTO(ArgumentMatchers.any())).thenReturn(new QuizObjectiveDTO());
        assertThat(service.retrieve(1l)).isInstanceOf(QuizObjectiveDTO.class);
    }

    @Test
    void retrieve_quiz_by_id_is_null() {
        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.retrieve(1l)).isInstanceOf(InvalidQuizInstanceException.class);
    }

    @Test
    void quizdto_is_null_when_updating_quiz_by_id() {
        assertThatThrownBy(() -> service.update(1l, null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void quiz_is_null_when_updating_quiz_by_id() {
        when(quizRepository.findById(1l)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.update(1l, new QuizDescriptiveDTO())).isInstanceOf(InvalidQuizInstanceException.class);
    }

    @Test
    void update_descriptive_quiz_by_id() {
        Quiz quiz = new Quiz();
        quiz.setDescriptiveAnswer(new DescriptiveAnswer());

        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(quiz));
        when(descriptiveService.update(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(new QuizDescriptiveDTO());
        assertThat(service.update(1l, new QuizDescriptiveDTO())).isInstanceOf(QuizDTO.class);
    }

    @Test
    void update_objective_quiz_by_id() {
        Quiz quiz = new Quiz();
        quiz.setObjectiveAnswer(new ObjectiveAnswer());

        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(quiz));
        when(objectiveService.update(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(new QuizObjectiveDTO());
        assertThat(service.update(1l, new QuizObjectiveDTO())).isInstanceOf(QuizDTO.class);
    }

    @Test
    void quiz_is_null_when_deleting_quiz_by_id() {
        when(quizRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.delete(1l)).isInstanceOf(InvalidQuizInstanceException.class);
    }
}
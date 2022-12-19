package com.example.jpa.quiz.service.strategy;

import com.example.jpa.quiz.constant.QType;
import com.example.jpa.quiz.domain.*;
import com.example.jpa.quiz.dto.*;
import com.example.jpa.quiz.exception.*;
import com.example.jpa.quiz.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class QuizObjectiveService implements QuizTypeStrategy {
    private final QuizRepository repository;
    private final ObjectiveAnswerRepository objectiveAnswerRepository;

    public QuizObjectiveService(QuizRepository repository, ObjectiveAnswerRepository objectiveAnswerRepository) {
        this.repository = repository;
        this.objectiveAnswerRepository = objectiveAnswerRepository;
    }

    @Override
    @Transactional
    public void add(QuizDTO dto) {
        try {
            tryAdd(dto);
        } catch (ClassCastException exception) {
            throw new InvalidObjectiveInstanceException();
        }
    }

    private void tryAdd(QuizDTO dto) {
        QuizObjectiveDTO data = (QuizObjectiveDTO) dto;

        try {
            Quiz quiz = tryAddQuiz(data);

            tryAddObjectiveAnswer(data, quiz);
        } catch (InvalidObjectiveAnswerInstanceException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new InvalidQuizInstanceException();
        }
    }

    private void tryAddObjectiveAnswer(QuizObjectiveDTO data, Quiz quiz) {
        try {
            ObjectiveAnswer objectiveAnswer = new ObjectiveAnswer();

            setAnswer(data, quiz, objectiveAnswer);

            quiz.setObjectiveAnswer(objectiveAnswerRepository.save(objectiveAnswer));
        } catch (Exception exception) {
            throw new InvalidObjectiveAnswerInstanceException();
        }
    }

    private Quiz tryAddQuiz(QuizObjectiveDTO data) {
        Quiz quiz = new Quiz();

        setQuiz(data, quiz);

        return repository.save(quiz);
    }

    @Override
    public QuizDTO toQuizDTO(Quiz quiz) {
        QuizObjectiveDTO dto = new QuizObjectiveDTO();

        dto.setQuestion(quiz.getQuestion());
        dto.setContent(quiz.getContent());
        dto.setQType(QType.OBJECTIVE);
        dto.setCategory(quiz.getCategory());

        if (Objects.nonNull(quiz.getObjectiveAnswer())) {
            dto.setAnswer(quiz.getObjectiveAnswer().getAnswer());
        }

        return dto;
    }

    @Override
    public QuizDTO update(Quiz quiz, QuizDTO dto) {
        QuizObjectiveDTO quizObjectiveDTO = (QuizObjectiveDTO) dto;

        setQuiz(quizObjectiveDTO, quiz);

        return toQuizDTO(quiz);
    }

    @Override
    public void delete(Quiz quiz) {
        repository.deleteById(quiz.getId());
    }

    private void setQuiz(QuizObjectiveDTO data, Quiz quiz) {
        quiz.setQuestion(data.getQuestion());
        quiz.setContent(data.getContent());
        quiz.setCategory(data.getCategory());

        ObjectiveAnswer objectiveAnswer = quiz.getObjectiveAnswer();

        if (Objects.nonNull(objectiveAnswer)) {
            objectiveAnswer.setAnswer(data.getAnswer());
        }
    }

    private void setAnswer(QuizObjectiveDTO data, Quiz quiz, ObjectiveAnswer objectiveAnswer) {
        objectiveAnswer.setAnswer(data.getAnswer());
        objectiveAnswer.setQuiz(quiz);
    }
}
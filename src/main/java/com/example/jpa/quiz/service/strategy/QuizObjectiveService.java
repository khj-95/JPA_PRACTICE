package com.example.jpa.quiz.service.strategy;

import com.example.jpa.quiz.contant.*;
import com.example.jpa.quiz.domain.*;
import com.example.jpa.quiz.dto.*;
import com.example.jpa.quiz.exception.*;
import com.example.jpa.quiz.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

            try {
                tryAddObjectiveAnswer(data, quiz);
            } catch (Exception exception) {
                throw new InvalidObjectiveAnswerInstanceException();
            }

        } catch (InvalidObjectiveAnswerInstanceException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new InvalidQuizInstanceException();
        }
    }

    private void tryAddObjectiveAnswer(QuizObjectiveDTO data, Quiz quiz) {
        ObjectiveAnswer objectiveAnswer = new ObjectiveAnswer();
        objectiveAnswer.setAnswer(data.getAnswer());
        objectiveAnswer.setQuiz(quiz);
        quiz.setObjectiveAnswer(objectiveAnswerRepository.save(objectiveAnswer));
    }

    private Quiz tryAddQuiz(QuizObjectiveDTO data) {
        Quiz quiz = new Quiz();
        quiz.setQuestion(data.getQuestion());
        quiz.setContent(data.getContent());
        quiz.setCategory(data.getCategory().name());
        return repository.save(quiz);
    }

    public QuizObjectiveDTO toQuizDTO(Quiz quiz) {
        QuizObjectiveDTO dto = new QuizObjectiveDTO();
        dto.setQuestion(quiz.getQuestion());
        dto.setContent(quiz.getContent());
        dto.setQType(QType.OBJECTIVE);
        dto.setCategory(Category.valueOf(quiz.getCategory()));
        dto.setAnswer(quiz.getObjectiveAnswer().getAnswer());

        return dto;
    }
}

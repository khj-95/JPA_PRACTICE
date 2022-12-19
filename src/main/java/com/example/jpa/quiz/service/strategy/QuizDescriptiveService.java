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
public class QuizDescriptiveService implements QuizTypeStrategy {
    private final QuizRepository repository;
    private final DescriptiveAnswerRepository descriptiveAnswerRepository;

    public QuizDescriptiveService(QuizRepository repository, DescriptiveAnswerRepository descriptiveAnswerRepository) {
        this.repository = repository;
        this.descriptiveAnswerRepository = descriptiveAnswerRepository;
    }

    @Override
    @Transactional
    public void add(QuizDTO dto) {
        try {
            tryAdd(dto);
        } catch (ClassCastException exception) {
            throw new InvalidDescriptiveInstanceException();
        }
    }

    private void tryAdd(QuizDTO dto) {
        QuizDescriptiveDTO data = (QuizDescriptiveDTO) dto;

        try {
            Quiz quiz = tryAddQuiz(data);

            tryAddDescriptiveAnswer(data, quiz);
        } catch (InvalidDescriptiveAnswerInstanceException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new InvalidQuizInstanceException();
        }
    }

    private void tryAddDescriptiveAnswer(QuizDescriptiveDTO data, Quiz quiz) {
        try {
            DescriptiveAnswer descriptiveAnswer = new DescriptiveAnswer();

            setAnswer(data, quiz, descriptiveAnswer);

            quiz.setDescriptiveAnswer(descriptiveAnswerRepository.save(descriptiveAnswer));
        } catch (Exception exception) {
            throw new InvalidDescriptiveAnswerInstanceException();
        }
    }

    private Quiz tryAddQuiz(QuizDescriptiveDTO data) {
        Quiz quiz = new Quiz();

        setQuiz(data, quiz);

        return repository.save(quiz);
    }

    @Override
    public QuizDTO toQuizDTO(Quiz quiz) {
        QuizDescriptiveDTO dto = new QuizDescriptiveDTO();

        dto.setQuestion(quiz.getQuestion());
        dto.setContent(quiz.getContent());
        dto.setQType(QType.DESCRIPTIVE);
        dto.setCategory(quiz.getCategory());

        if (Objects.nonNull(quiz.getDescriptiveAnswer())) {
            dto.setAnswer(quiz.getDescriptiveAnswer().getAnswer());
        }

        return dto;
    }

    @Override
    @Transactional
    public QuizDTO update(Quiz quiz, QuizDTO dto) {
        QuizDescriptiveDTO quizDescriptiveDTO = (QuizDescriptiveDTO) dto;

        setQuiz(quizDescriptiveDTO, quiz);

        return toQuizDTO(quiz);
    }

    @Override
    public void delete(Quiz quiz) {
        repository.deleteById(quiz.getId());
    }

    private void setQuiz(QuizDescriptiveDTO data, Quiz quiz) {
        quiz.setQuestion(data.getQuestion());
        quiz.setContent(data.getContent());
        quiz.setCategory(data.getCategory());

        DescriptiveAnswer descriptiveAnswer = quiz.getDescriptiveAnswer();

        if (Objects.nonNull(descriptiveAnswer)) {
            descriptiveAnswer.setAnswer(data.getAnswer());
        }
    }

    private void setAnswer(QuizDescriptiveDTO data, Quiz quiz, DescriptiveAnswer descriptiveAnswer) {
        descriptiveAnswer.setAnswer(data.getAnswer());
        descriptiveAnswer.setQuiz(quiz);
    }
}
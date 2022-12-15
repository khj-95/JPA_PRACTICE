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

            try {
                tryAddDescriptiveAnswer(data, quiz);
            } catch (Exception exception) {
                throw new InvalidDescriptiveAnswerInstanceException();
            }

        } catch (InvalidDescriptiveAnswerInstanceException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new InvalidQuizInstanceException();
        }
    }

    private void tryAddDescriptiveAnswer(QuizDescriptiveDTO data, Quiz quiz) {
        DescriptiveAnswer descriptiveAnswer = new DescriptiveAnswer();
        descriptiveAnswer.setAnswer(data.getAnswer());
        descriptiveAnswer.setQuiz(quiz);
        quiz.setDescriptiveAnswer(descriptiveAnswerRepository.save(descriptiveAnswer));
    }


    private Quiz tryAddQuiz(QuizDescriptiveDTO data) {
        Quiz quiz = new Quiz();
        quiz.setQuestion(data.getQuestion());
        quiz.setContent(data.getContent());
        quiz.setCategory(data.getCategory());
        return repository.save(quiz);
    }

    public QuizDescriptiveDTO toQuizDTO(Quiz quiz) {
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

    @Transactional
    public QuizDTO update(Quiz quiz, QuizDTO dto) {
        QuizDescriptiveDTO quizDescriptiveDTO = (QuizDescriptiveDTO) dto;

        quiz.setQuestion(quizDescriptiveDTO.getQuestion());
        quiz.setContent(quizDescriptiveDTO.getContent());
        quiz.setCategory(quizDescriptiveDTO.getCategory());
        quiz.getDescriptiveAnswer().setAnswer(quizDescriptiveDTO.getAnswer());

        return toQuizDTO(quiz);
    }

    public void delete(Quiz quiz) {
        repository.deleteById(quiz.getId());
    }
}

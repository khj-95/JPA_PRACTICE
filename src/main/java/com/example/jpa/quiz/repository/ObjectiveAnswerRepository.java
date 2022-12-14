package com.example.jpa.quiz.repository;

import com.example.jpa.quiz.domain.ObjectiveAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectiveAnswerRepository extends CrudRepository<ObjectiveAnswer, Long> {
}

package com.example.jpa.quiz.repository;

import com.example.jpa.quiz.domain.DescriptiveAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptiveAnswerRepository extends CrudRepository<DescriptiveAnswer, Long> {
}

package com.example.jpa.quiz.controller;

import com.example.jpa.quiz.dto.QuizDTO;
import com.example.jpa.quiz.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("quiz")
@RestController
public class QuizApiController {
    private final QuizService service;

    public QuizApiController(QuizService service) {
        this.service = service;
    }

    @PostMapping
    public void add(QuizDTO dto) {
        /*dto.setQuestion("test Question");
        dto.setContent("test content");
        dto.setCategory(Category.JAVA);
        dto.setQType(QType.DESCRIPTIVE);
        dto.setAnswer("test answer");*/

        service.add(dto);
    }

    @GetMapping("{id}")
    public QuizDTO retrieve(@PathVariable("id") Long id) {
        return service.retrieve(id);
    }
}

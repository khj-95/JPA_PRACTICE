package com.example.jpa.quiz.controller;

import com.example.jpa.quiz.dto.*;
import com.example.jpa.quiz.service.QuizService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("quiz")
@RestController
public class QuizController {
    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/add/d")
    public void add(QuizDescriptiveDTO dto) {
        service.add(dto);
    }
    @GetMapping("/add/o")
    public void add(QuizObjectiveDTO dto) {
        service.add(dto);
    }
}

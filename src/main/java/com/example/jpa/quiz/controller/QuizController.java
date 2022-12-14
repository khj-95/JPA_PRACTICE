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
        /*dto.setQuestion("test Question");
        dto.setContent("test content");
        dto.setCategory(Category.JAVA);
        dto.setQType(QType.DESCRIPTIVE);
        dto.setAnswer("test answer");*/

        service.add(dto);
    }

    @GetMapping("/add/o")
    public void add(QuizObjectiveDTO dto) {
        /*dto.setQuestion("test Question");
        dto.setContent("test content");
        dto.setCategory(Category.SQL);
        dto.setQType(QType.OBJECTIVE);
        dto.setAnswer(1);*/

        service.add(dto);
    }

    @GetMapping("/retrieve/{id}")
    @ResponseBody
    public QuizDTO retrieve(@PathVariable("id") Long id) {
        return service.retrieve(id);
    }
}

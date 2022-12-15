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

    @PostMapping("add")
    public void add(@RequestBody QuizDTO dto) {
        /*QuizDescriptiveDTO test = new QuizDescriptiveDTO();
        test.setQuestion("test Question");
        test.setContent("test content");
        test.setCategory(Category.JAVA);
        test.setQType(QType.DESCRIPTIVE);
        test.setAnswer("test answer");*/

        service.add(dto);
    }

    @GetMapping("{id}")
    public QuizDTO retrieve(@PathVariable("id") Long id) {
        return service.retrieve(id);
    }

    @PostMapping("update/{id}")
    public QuizDTO update(@PathVariable("id") Long id, @RequestBody QuizDTO dto) {
        /*QuizDescriptiveDTO test = new QuizDescriptiveDTO();
        test.setQuestion("test update Question");
        test.setContent("test update content");
        test.setCategory(Category.SQL);
        test.setAnswer("test update answer");*/

        return service.update(id, dto);
    }

    @PostMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}

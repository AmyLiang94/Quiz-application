package com.example.Quiz.application.controller;

import com.example.Quiz.application.model.Question;
import com.example.Quiz.application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PutMapping("update/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable int questionId,@RequestBody Question updatedQuestion){
        return questionService.updateQuestion(questionId, updatedQuestion);
    }

    @DeleteMapping("Delete/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int questionId){
        return questionService.deleteQuestion(questionId);
    }

}

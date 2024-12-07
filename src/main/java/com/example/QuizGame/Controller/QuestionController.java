package com.example.QuizGame.Controller;

import com.example.QuizGame.Model.QuestionDb;
import com.example.QuizGame.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public ResponseEntity<List<QuestionDb>> allQuestions(){
        return ResponseEntity.ok(questionService.findallquestions());
    }
    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionDb> findQuestionById(@PathVariable Long id){
        return ResponseEntity.ok(questionService.findQuestion(id));
    }

    @PostMapping("/add-question")
    public ResponseEntity<QuestionDb> addQuestion(@RequestBody QuestionDb questionDb){
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(questionDb));
    }

    @PutMapping("/update-question/{id}")
    public ResponseEntity<QuestionDb> updateQuestion(@PathVariable Long id , @RequestBody QuestionDb questionDb){
        return ResponseEntity.ok(questionService.updateQuestion(id,questionDb));
    }

    @DeleteMapping("/delete-question/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.status(HttpStatus.OK).body("question deleted");
        }
        catch ( IllegalArgumentException e){
            throw new RuntimeException("invalid job id");
        }
    }
}

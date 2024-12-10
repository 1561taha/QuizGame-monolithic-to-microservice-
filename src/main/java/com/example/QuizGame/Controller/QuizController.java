package com.example.QuizGame.Controller;

import com.example.QuizGame.Model.QuestionDto;
import com.example.QuizGame.Model.Quiz;
import com.example.QuizGame.Model.QuizDto;
import com.example.QuizGame.Model.QuizResultDto;
import com.example.QuizGame.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> GetQuizById(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Quiz> CreateQuiz(@RequestParam String quizTitle,
                                           @RequestParam String category,
                                           @RequestParam String difficulty) {
        return new ResponseEntity<>(quizService.createQuiz(quizTitle, category, difficulty), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteQuiz(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.deleteQuizByid(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> allQuiz(){
        return new ResponseEntity<>(quizService.getAllQUiz(),HttpStatus.OK);
    }
    @PostMapping("/{quizid}/play")
    public ResponseEntity<QuizResultDto> playquiz(@PathVariable Long quizid , @RequestBody Map<Long, Integer> userAnswers){
        return new ResponseEntity<>(quizService.quizResult(quizid,userAnswers),HttpStatus.OK);
    }
}

package com.example.QuizGame.Service;

import com.example.QuizGame.Model.QuestionDb;
import com.example.QuizGame.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;
    public List<QuestionDb> findallquestions() {
        return questionRepo.findAll();
    }

    public QuestionDb findQuestion(Long id) {
        return questionRepo.findById(id).orElseThrow(()-> new RuntimeException("invalid question id"));
    }

    public QuestionDb createQuestion(QuestionDb questionDb) {
        return questionRepo.save(questionDb);
    }

    public QuestionDb updateQuestion(Long id, QuestionDb questionDb) {
        return questionRepo.findById(id).map(existingQuestion ->{
            existingQuestion.setQuestionTitle(questionDb.getQuestionTitle());
            existingQuestion.setOptions(questionDb.getOptions());
            existingQuestion.setCategory(questionDb.getCategory());
            existingQuestion.setCorrectOption(questionDb.getCorrectOption());
            existingQuestion.setDifficulty(questionDb.getDifficulty());

            return questionRepo.save(existingQuestion);


        }).orElseThrow(()-> new RuntimeException("invalid job id"));

    }

    public void deleteQuestion(Long id) {
        questionRepo.deleteById(id);
    }
}

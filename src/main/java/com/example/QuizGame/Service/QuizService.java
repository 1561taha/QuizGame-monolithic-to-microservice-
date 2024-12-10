package com.example.QuizGame.Service;

import com.example.QuizGame.Model.*;
import com.example.QuizGame.Repo.QuestionRepo;
import com.example.QuizGame.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
  private QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;
    public QuizDto findById(Long id) {
        Quiz ogquiz=quizRepo.findById(id).orElseThrow(()-> new RuntimeException("invalid quiz id"));

        List<QuestionDto> questionDtos=ogquiz.getQuestions()
                .stream()
                .map(q->new QuestionDto(q.getId(),q.getQuestionTitle(),q.getOptions(),q.getCategory(),q.getDifficulty()))
                .collect(Collectors.toList());

        return new QuizDto
                (ogquiz.getId(), ogquiz.getQuizTitle(), ogquiz.getCategory(),questionDtos, ogquiz.getDifficulty());


    }

    public Quiz createQuiz(String quizTitle, String category, String difficulty) {
        Quiz quiz= new Quiz();

        List<QuestionDb> questions= questionRepo.findByCategory(category);
        quiz.setQuizTitle(quizTitle);
        quiz.setCategory(category);
        quiz.setDifficulty(difficulty);
        quiz.setQuestions(questions);

        return quizRepo.save(quiz);
    }


    public String deleteQuizByid(Long id) {
        try {
            quizRepo.deleteById(id);
            return "deleted quiz";
        }
        catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public List<Quiz> getAllQUiz() {
        return quizRepo.findAll();
    }

    public QuizResultDto quizResult(Long id, Map<Long, Integer> userAnswers) {
        Quiz quiz=quizRepo.findById(id).orElseThrow(()->new RuntimeException("invalid quiz id"));

        int totalQuestions=quiz.getQuestions().size();

        int score=0;

        for (QuestionDb question: quiz.getQuestions()){
            Integer userAnswer=userAnswers.get(question.getId());
            if (userAnswer!=null &&
                    userAnswer.equals(question.getCorrectOption().intValue()))
                score++;
        }
        return new QuizResultDto(quiz.getId(),totalQuestions,score);
    }
}

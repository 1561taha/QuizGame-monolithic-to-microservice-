package com.example.QuizGame.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDto {

    private Long quizId;
    private int totalQuestions;
    private int score;
}

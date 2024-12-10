package com.example.QuizGame.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long  id;
    private  String quizTitle;
    private String category;
    private List<QuestionDto> questionDtos;
    private String difficulty;
}

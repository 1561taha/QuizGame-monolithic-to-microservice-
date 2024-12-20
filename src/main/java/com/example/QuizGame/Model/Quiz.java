package com.example.QuizGame.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String quizTitle;

    @Column(nullable = false)
    private String category;

    @ManyToMany
    private List<QuestionDb> questions;

    @Column(nullable = false)
    private  String difficulty;

}
package com.example.QuizGame.Repo;

import com.example.QuizGame.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo  extends JpaRepository<Quiz,Long> {
}

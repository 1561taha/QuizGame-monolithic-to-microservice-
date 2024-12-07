package com.example.QuizGame.Repo;

import com.example.QuizGame.Model.QuestionDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<QuestionDb,Long>{
}

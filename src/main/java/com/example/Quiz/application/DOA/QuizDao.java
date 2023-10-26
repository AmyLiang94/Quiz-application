package com.example.Quiz.application.DOA;

import com.example.Quiz.application.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}

package com.example.Quiz.application.service;

import com.example.Quiz.application.DOA.QuestionDao;
import com.example.Quiz.application.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateQuestion(int questionId, Question updatedQuestion){
        Optional<Question> optionalExistingQuestion = questionDao.findById(questionId);
        Question existingQuestion = optionalExistingQuestion.get();

        if(existingQuestion != null){
            existingQuestion.setQuestionTitles(updatedQuestion.getQuestionTitles());
            existingQuestion.setCategory(updatedQuestion.getCategory());
            questionDao.save(existingQuestion);
            return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteQuestion(int questionId){
        Optional<Question> optionExistingQuestion = questionDao.findById(questionId);
        Question existingQuestion = optionExistingQuestion.get();

        if(existingQuestion != null){
            questionDao.delete(existingQuestion);
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
    }
}

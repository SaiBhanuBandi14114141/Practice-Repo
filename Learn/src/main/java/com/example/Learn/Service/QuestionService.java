package com.example.Learn.Service;

import com.example.Learn.Models.Questions;
import com.example.Learn.Repository.QuestionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.Learn.Utils.CoreUtils.getNullPropertyNames;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionsRepository;

    public List<Questions> getAllQuestions() {
        try {
            return questionsRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    public Optional<Questions> getQuestionsById(Long id) {
        try {
            Questions question = questionsRepository.findById(id).orElse(null);
            if (question != null) {
                return questionsRepository.findById(id);
            }
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public Questions saveQuestion(Questions question) {
        try {
            return questionsRepository.save(question);
        } catch (Exception e) {
            return null;
        }
    }

    /*public Questions updateQuestion(Long id, Questions updatedQuestion) {
        Questions question = questionsRepository.findById(id).orElse(null);
        if (question != null) {
            if (updatedQuestion.getQuestion() != null) {
                question.setQuestion(updatedQuestion.getQuestion());
            }
            if (updatedQuestion.getOption1() != null) {
                question.setOption1(updatedQuestion.getOption1());
            }
            if (updatedQuestion.getOption2() != null) {
                question.setOption2(updatedQuestion.getOption2());
            }
            if (updatedQuestion.getRemarks() != null) {
                question.setRemarks(updatedQuestion.getRemarks());
            }
            return questionsRepository.save(question);
        }
        return null;
    }*/

    public Questions updateQuestion(Long id, Questions updatedQuestion) {
        try {
            Questions question = questionsRepository.findById(id).orElse(null);
            if (question != null) {
                BeanUtils.copyProperties(updatedQuestion, question, getNullPropertyNames(updatedQuestion));
                return questionsRepository.save(question);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public Questions deleteQuestion(Long id) {
        try {
            Questions question = questionsRepository.findById(id).orElse(null);
            if (question != null) {
                questionsRepository.deleteById(id);
            }
            return question;
        } catch (Exception e) {
            return null;
        }
    }
}

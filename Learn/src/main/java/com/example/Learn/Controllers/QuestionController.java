package com.example.Learn.Controllers;

import com.example.Learn.Models.Questions;
import com.example.Learn.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class QuestionController {

    @Autowired
    private QuestionService QuestionService;

    @GetMapping("/getAllQuestions")
    public Map<String, Object> getAllQuestions() {
        Map<String, Object> response = new HashMap<>();
        List<Questions> Questions = QuestionService.getAllQuestions();
        response.put("Questions", Questions);
        return response;
    }

    @GetMapping("/getQuestionsById/{id}")
    public Map<String, Object> getQuestionsByKeyword(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Questions> Questions = QuestionService.getQuestionsById(id);
        response.put("Questions", Questions);
        return response;
    }

    @PostMapping("/addQuestion")
    public Map<String, Object> addQuestion(@RequestBody Questions question) {
        Map<String, Object> response = new HashMap<>();
        Questions savedQuestion = QuestionService.saveQuestion(question);
        response.put("question", savedQuestion);
        return response;
    }

    @PatchMapping("/updateQuestion/{id}")
    public Map<String, Object> updateQuestion(@PathVariable Long id, @RequestBody Questions updatedQuestion) {
        Map<String, Object> response = new HashMap<>();
        Questions question = QuestionService.updateQuestion(id, updatedQuestion);
        response.put("question", question);
        return response;
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public Map<String, Object> deleteQuestion(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Questions deletedQuestion = QuestionService.deleteQuestion(id);
        response.put("question", deletedQuestion);
        return response;
    }

}

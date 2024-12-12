package com.vignesh.conceptile_assignment.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.conceptile_assignment.model.Question;
import com.vignesh.conceptile_assignment.model.QuestionDTO;
import com.vignesh.conceptile_assignment.model.UserAnswer;
import com.vignesh.conceptile_assignment.services.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

     // Start a new quiz session
    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> startQuiz() {
        Long userId = quizService.startQuiz();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Quiz session started!");
        response.put("userId", userId);
        return ResponseEntity.ok(response);
    }

    // Get a random question
   @GetMapping("/question")
    public ResponseEntity<QuestionDTO> getRandomQuestion(@RequestParam Long userId) {
        Optional<Question> question = quizService.getRandomQuestion();
        if (question.isPresent()) {
            Question q = question.get();
            QuestionDTO questionDTO = new QuestionDTO(
                q.getId(),
                q.getQuestionText(),
                q.getOptionA(),
                q.getOptionB(),
                q.getOptionC(),
                q.getOptionD()
            );
            return ResponseEntity.ok(questionDTO);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/answer")
    public ResponseEntity<Map<String, Object>> submitAnswer(@RequestParam Long userId,
                                                            @RequestParam Long questionId,
                                                            @RequestParam String selectedOption) {
        UserAnswer userAnswer = quizService.submitAnswer(userId, questionId, selectedOption);
        if (userAnswer == null) {
            return ResponseEntity.status(404).build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Answer submitted successfully!");
        response.put("isCorrect", userAnswer.isCorrect());
        response.put("correctOption", userAnswer.getQuestion().getCorrectOption());
        return ResponseEntity.ok(response);
    }

    // Get the user's score (total answered, correct, incorrect)
    @GetMapping("/score")
    public ResponseEntity<Map<String, Integer>> getUserScore(@RequestParam Long userId) {
        Map<String, Integer> score = quizService.getUserScore(userId);
        return ResponseEntity.ok(score);
    }
}

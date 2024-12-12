package com.vignesh.conceptile_assignment.services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vignesh.conceptile_assignment.model.Question;
import com.vignesh.conceptile_assignment.model.UserAnswer;
import com.vignesh.conceptile_assignment.repos.QuestionRepository;
import com.vignesh.conceptile_assignment.repos.UserAnswerRepository;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    // Start a new quiz session
    public Long startQuiz() {
        // For simplicity, we assume userId is 1 for this session. 
        // In a real-world scenario, you could generate or track user sessions dynamically.
        return 1L; // Return a mock userId
    }

    // Get a random question
    public Optional<Question> getRandomQuestion() {
        // Fetch a random question from the database
        return questionRepository.findRandomQuestion();
    }

    // Submit an answer
    public UserAnswer submitAnswer(Long userId, Long questionId, String selectedOption) {
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (questionOpt.isPresent()) {
            Question question = questionOpt.get();
            boolean isCorrect = selectedOption.equals(question.getCorrectOption());

            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setQuestion(question);
            userAnswer.setUserId(userId);
            userAnswer.setSelectedOption(selectedOption);
            userAnswer.setCorrect(isCorrect);

            return userAnswerRepository.save(userAnswer);
        }
        return null;
    }

    // Get the user's score (total answered, correct, incorrect)
    public Map<String, Integer> getUserScore(Long userId) {
        List<UserAnswer> answers = userAnswerRepository.findByUserId(userId);
        int totalAnswered = answers.size();
        int correctAnswers = (int) answers.stream().filter(UserAnswer::isCorrect).count();
        int incorrectAnswers = totalAnswered - correctAnswers;

        Map<String, Integer> scoreDetails = new HashMap<>();
        scoreDetails.put("totalAnswered", totalAnswered);
        scoreDetails.put("correctAnswers", correctAnswers);
        scoreDetails.put("incorrectAnswers", incorrectAnswers);

        return scoreDetails;
    }
}

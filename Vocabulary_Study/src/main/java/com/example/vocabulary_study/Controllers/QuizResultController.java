package com.example.vocabulary_study.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.util.List;

import com.example.vocabulary_study.Models.QuizService;
import com.example.vocabulary_study.Models.QuizService.Question;

public class QuizResultController {
    @FXML
    private Label scoreLabel;
    
    @FXML
    private Label timeLabel;
    
    @FXML
    private ListView<String> correctList;
    
    @FXML
    private ListView<String> incorrectList;
    
    @FXML
    private Button retryButton;
    
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;
    

    private Runnable onRetry;
    private Runnable onBack;

    //Nút next
    @FXML
private void handleNextQuestion() {
    if (currentIndex < questions.size() - 1) {
        currentIndex++;
        loadQuestion(questions.get(currentIndex)); // Chuyển sang câu hỏi tiếp theo
    } else {
        nextButton.setDisable(true); // Nếu hết câu hỏi thì vô hiệu hóa nút
    }
}

    //load câu hỏi lên màn hình
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton option1;
    @FXML
    private RadioButton option2;
    @FXML
    private RadioButton option3;
    
    private ToggleGroup toggleGroup = new ToggleGroup();
    private List<Question> questions;
    private int currentIndex = 0;

    public void initialize() {
        // Gán ToggleGroup để chỉ chọn 1 đáp án
        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);
        
        // Lấy danh sách câu hỏi từ QuizService
        QuizService quizService = new QuizService();
        questions = quizService.getQuizQuestions(1); // Thay `1` bằng user_id thực tế

        if (!questions.isEmpty()) {
            loadQuestion(questions.get(currentIndex));
        }

        nextButton.setOnAction(e -> handleNextQuestion());
    }

    private void loadQuestion(Question question) {
        questionLabel.setText(question.word); // Hiển thị từ vựng
        option1.setText(question.options.get(0));
        option2.setText(question.options.get(1));
        option3.setText(question.options.get(2));
    }


    public void setResult(int score, int totalQuestions, String timeTaken, List<String> correctAnswers, List<String> incorrectAnswers) {
        scoreLabel.setText("Score: " + score + "/" + totalQuestions);
        timeLabel.setText("Time: " + timeTaken);
        correctList.getItems().addAll(correctAnswers);
        incorrectList.getItems().addAll(incorrectAnswers);
    }

    public void setOnRetry(Runnable onRetry) {
        this.onRetry = onRetry;
        retryButton.setOnAction(e -> {
            if (onRetry != null) onRetry.run();
            ((Stage) retryButton.getScene().getWindow()).close();
        });
    }

    public void setOnBack(Runnable onBack) {
        this.onBack = onBack;
        backButton.setOnAction(e -> {
            if (onBack != null) onBack.run();
            ((Stage) backButton.getScene().getWindow()).close();
        });
    }
}

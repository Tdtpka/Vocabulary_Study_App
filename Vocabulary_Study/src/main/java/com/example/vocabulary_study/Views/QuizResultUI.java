package com.example.vocabulary_study.Views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.vocabulary_study.Models.QuizService;
import com.example.vocabulary_study.Models.QuizService.Question;
import java.util.ArrayList;
import java.util.List;

public class QuizResultUI extends Application {
    private int correctAnswers = 0;
    private int totalQuestions;
    private long startTime;
    private List<Question> questions;
    private int currentIndex = 0;
    private List<String> correctList = new ArrayList<>();
    private List<String> wrongList = new ArrayList<>();

    private Label questionLabel;
    private RadioButton option1, option2, option3;
    private ToggleGroup toggleGroup;
    private Button nextButton;
    private Label resultLabel;

    @Override
    public void start(Stage stage) {
        QuizService quizService = new QuizService();
        questions = quizService.getQuizQuestions(1);
        totalQuestions = questions.size();
        startTime = System.currentTimeMillis();

        questionLabel = new Label();
        option1 = new RadioButton();
        option2 = new RadioButton();
        option3 = new RadioButton();
        toggleGroup = new ToggleGroup();
        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);
        
        nextButton = new Button("Tiếp tục");
        resultLabel = new Label();
        resultLabel.setVisible(false);

        nextButton.setOnAction(e -> checkAnswer());

        VBox root = new VBox(10, questionLabel, option1, option2, option3, nextButton, resultLabel);
        root.setStyle("-fx-padding: 20;");
        Scene scene = new Scene(root, 400, 500);

        stage.setTitle("Bài kiểm tra");
        stage.setScene(scene);
        stage.show();

        if (!questions.isEmpty()) {
            loadQuestion(questions.get(currentIndex));
        }
    }

    private void loadQuestion(Question question) {
        questionLabel.setText(question.word);
        option1.setText(question.options.get(0));
        option2.setText(question.options.get(1));
        option3.setText(question.options.get(2));
        toggleGroup.selectToggle(null);
    }

    private void checkAnswer() {
    RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
    if (selected == null) {
        resultLabel.setText("Vui lòng chọn một đáp án!");
        resultLabel.setVisible(true);
        return;
    }

    String selectedAnswer = selected.getText();
    Question currentQuestion = questions.get(currentIndex);

    if (selectedAnswer.equals(currentQuestion.correctAnswer)) {
        correctAnswers++;
        correctList.add(currentQuestion.word + " - " + currentQuestion.correctAnswer);
    } else {
        wrongList.add(currentQuestion.word + " - Đáp án đúng: " + currentQuestion.correctAnswer);
    }

    currentIndex++;

    if (currentIndex < questions.size()) {
        loadQuestion(questions.get(currentIndex));  // Chuyển sang câu tiếp theo
    } else {
        showFinalResult(); // Gọi hiển thị kết quả khi hết câu
    }
}

    

    private void showFinalResult() {
        long timeTaken = (System.currentTimeMillis() - startTime) / 1000; // Tính thời gian làm bài
        
        StringBuilder resultText = new StringBuilder();
        resultText.append("Bạn đã trả lời đúng ").append(correctAnswers).append("/")
                .append(totalQuestions).append(" câu.\n");
        resultText.append("Thời gian làm bài: ").append(timeTaken / 60).append(" phút ")
                .append(timeTaken % 60).append(" giây.\n\n");
    
        if (!wrongList.isEmpty()) {
            resultText.append("Các câu sai:\n");
            for (String wrong : wrongList) {
                resultText.append(wrong).append("\n");
            }
        } else {
            resultText.append("Chúc mừng! Bạn đã trả lời đúng tất cả các câu!");
        }
    
        // Vô hiệu hóa nút "Tiếp tục" và cập nhật giao diện hiển thị kết quả
        nextButton.setDisable(true);
        resultLabel.setText(resultText.toString());
        resultLabel.setVisible(true);
    }
    
}

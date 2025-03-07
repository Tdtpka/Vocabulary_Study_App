package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Quiz;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class QuizCellController implements Initializable {
    private final Quiz quiz;
    public Label word_label = new Label();
    public TextField answer_field = new TextField();
    public QuizCellController(Quiz quiz){
        this.quiz = quiz;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        word_label.setText(quiz.getVocabulary().wordProperty().get());
        answer_field.setText(quiz.answerProperty().get());
        answer_field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if(Objects.equals(answer_field.getText(), quiz.getVocabulary().meaningProperty().get())){
                    quiz.isCorrectProperty().set(true);
                }else{
                    quiz.isCorrectProperty().set(false);
                }
                quiz.answerProperty().set(answer_field.getText());
            }
        });
    }

}

package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Models.Quiz;
import com.example.vocabulary_study.Models.UserDictionary;
import com.example.vocabulary_study.Models.Vocabulary;
import com.example.vocabulary_study.Views.QuizCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class QuizDataController implements Initializable {
    private final UserDictionary userDictionary;
    private final ObservableList<Quiz> list_quiz = FXCollections.observableArrayList();
    public Button quit_btn;
    public Button submit_btn;
    public Label date_label;
    public ListView<Quiz> list_view;
    public Label dictionary_name_label;

    public QuizDataController(UserDictionary userDictionary){
        this.userDictionary = userDictionary;
    }

    @Override
    public void  initialize(URL url, ResourceBundle resourceBundle){
        ObservableList<Vocabulary> vocabularies = Model.getDictionaryVocabularies(userDictionary.dictionaryIDProperty().get());
        for(int i = 0; i<vocabularies.size(); i++){
            Quiz quiz = new Quiz(vocabularies.get(i), false);
            list_quiz.add(quiz);
        }
        date_label.setText(LocalDate.now().toString());
        dictionary_name_label.setText(userDictionary.dictionaryNameProperty().get());
        quit_btn.setOnAction(event->onQuit());
        submit_btn.setOnAction(event->onSubmit());
        list_view.setItems(list_quiz);
        list_view.setCellFactory(list_view-> new QuizCellFactory());
    }

    private void onSubmit() {
        Model.updateQuizResults(list_quiz, userDictionary.dictionaryIDProperty().get());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Đã nộp bài quiz!");
        Optional<ButtonType> clickOK = alert.showAndWait();
        if(clickOK.isPresent() && clickOK.get() == ButtonType.OK){
            Stage stage = (Stage) submit_btn.getScene().getWindow();
            stage.close();
        }
    }

    private void onQuit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Bạn muốn thoát bài kiểm tra?");
        alert.setContentText("Kết quả sẽ không được lưu");
        Optional<ButtonType> quit = alert.showAndWait();
        if(quit.isPresent()&&quit.get().getButtonData()== ButtonBar.ButtonData.OK_DONE){
            Stage stage = (Stage) quit_btn.getScene().getWindow();
            stage.close();
        }
    }
}

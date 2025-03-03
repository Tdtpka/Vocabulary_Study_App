package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Views.UserMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AppMenuController implements Initializable {
    public Button home_btn;
    public Button quiz_btn;
    public Button word_btn;
    public Button result_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        home_btn.setOnAction(event->onHome());
        word_btn.setOnAction(event->onWord());
        quiz_btn.setOnAction(event->onQuiz());
        result_btn.setOnAction(actionEvent -> onResult());
    }
    private void onHome(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.HOME);
    }
    private void onWord(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.WORD);
    }
    private void onQuiz(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.QUIZ);
    }
    private void onResult(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.RESULT);
    }

}

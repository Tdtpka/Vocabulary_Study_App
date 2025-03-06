package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    public BorderPane app_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().addListener((observableValue, oldVal, newVal)->{
            switch (newVal){
                case QUIZ -> app_parent.setCenter(Model.getInstance().getViewFactory().getQuizView());
                case WORD -> app_parent.setCenter(Model.getInstance().getViewFactory().getDictionaryView());
                case RESULT -> app_parent.setCenter(Model.getInstance().getViewFactory().getResultView());
                case DICTIONARY -> app_parent.setCenter(Model.getInstance().getViewFactory().getDictionaryWordView());
                default -> app_parent.setCenter(Model.getInstance().getViewFactory().getHomeView());
            }
        });
    }
}

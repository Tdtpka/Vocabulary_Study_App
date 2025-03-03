package com.example.vocabulary_study.Views;

import com.example.vocabulary_study.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showSignInWindow();

    }

    public static void main(String[] args) {
        launch();
    }
}
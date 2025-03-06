package com.example.vocabulary_study.Views;

import com.example.vocabulary_study.Models.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showSignInWindow();
    }

    // @Override
    // public void start(Stage primaryStage) throws Exception {
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/fxml/quiz.fxml"));
    //     Parent root = loader.load();
    //     primaryStage.setScene(new Scene(root));
    //     primaryStage.setTitle("Quiz");
    //     primaryStage.show();
    // }
    
}

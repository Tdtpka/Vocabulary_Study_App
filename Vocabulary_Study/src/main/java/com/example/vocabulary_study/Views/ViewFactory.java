package com.example.vocabulary_study.Views;

import com.example.vocabulary_study.Controllers.AppController;
import com.example.vocabulary_study.Controllers.DictionaryWordController;
import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Models.UserDictionary;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private ObjectProperty<UserMenuOptions> userSelectedMenuItem;
    private Pane home;
    private Pane dictionary;
    private Pane dictionaryWord;
    private Pane quiz;
    private Pane result;

    public ViewFactory(){
        this.userSelectedMenuItem = new SimpleObjectProperty<>();
    }
    public ObjectProperty<UserMenuOptions> getUserSelectedMenuItem(){
        return userSelectedMenuItem;
    }
    public void showSignInWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/signin.fxml"));
        createStage(loader);
    }
    public void showSignUpWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/signup.fxml"));
        createStage(loader);
    }
    public void showAppWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/app.fxml"));
        AppController appController = new AppController();
        loader.setController(appController);
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Vocabulary App");
        stage.show();
    }
    public void closeStage(Stage stage){
        stage.close();
    }
    public Pane getHomeView(){
        if(home==null){
            try{
                home = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/home.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return home;
    }
    public Pane getDictionaryView(){
        if(dictionary==null){
            try {
                dictionary = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/dictionary.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dictionary;
    }
    public Pane getDictionaryWordView(){
        try{
            dictionaryWord = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/dictionary_word.fxml")).load();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dictionaryWord;
    }
    public  Pane getQuizView(){
        if (quiz==null){
            try {
                quiz = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/quiz.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return quiz;
    }
    public Pane getResultView(){
        if (result==null){
            try{
                result = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/result.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public void showCreateDictionaryWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/create_dictionary.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Create New Dictionary");
            stage.setOnCloseRequest(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn muốn hủy tạo từ điển?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.CANCEL) {
                        event.consume(); // Ngăn cửa sổ đóng lại
                    }
                });
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

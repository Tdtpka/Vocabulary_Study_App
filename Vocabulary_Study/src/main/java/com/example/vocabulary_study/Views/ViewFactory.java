package com.example.vocabulary_study.Views;

import com.example.vocabulary_study.Controllers.AppController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewFactory {
    private ObjectProperty<UserMenuOptions> userSelectedMenuItem;
    private Pane home;
    private Pane dictionary;
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
}

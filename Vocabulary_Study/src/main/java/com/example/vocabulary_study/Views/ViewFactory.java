package com.example.vocabulary_study.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {
    private AnchorPane home;
    private AnchorPane dictionary;
    private AnchorPane quiz;
    private AnchorPane result;

    public ViewFactory(){
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
    public AnchorPane getHomeView(){
        if(home==null){
            try{
                home = new FXMLLoader(getClass().getResource("com/example/vocabulary_study/Fxml/home.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return home;
    }
    public AnchorPane getDictionaryView(){
        if(dictionary==null){
            try {
                dictionary = new FXMLLoader(getClass().getResource("com/example/vocabulary_study/Fxml/dictionary.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dictionary;
    }
    public  AnchorPane getQuizView(){
        if (quiz==null){
            try {
                quiz = new FXMLLoader(getClass().getResource("com/example/vocabulary_study/Fxml/quiz.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return quiz;
    }
    public AnchorPane getResultView(){
        if (result==null){
            try{
                result = new FXMLLoader(getClass().getResource("com/example/vocabulary_study/Fxml/result.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}

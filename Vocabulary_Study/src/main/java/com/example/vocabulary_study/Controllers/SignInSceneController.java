package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInSceneController implements Initializable {
    public TextField acc_field;
    public Label forgot_pass;
    public Button sign_in_btn;
    public TextField pass_field;
    public Button sign_up_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sign_in_btn.setOnAction(event->onSignIn());
        sign_up_btn.setOnAction(event->onSignUp());
    }
    private void onSignIn(){
        Stage stage = (Stage) sign_in_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().showAppWindow();
        Model.getInstance().getViewFactory().closeStage(stage);

    }
    private void onSignUp(){
        Stage stage = (Stage) sign_up_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignUpWindow();
    }

}

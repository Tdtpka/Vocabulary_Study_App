package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignInSceneController implements Initializable {
    public TextField acc_field;
    public Label forgot_pass;
    public Button sign_in_btn;
    public TextField pass_field;
    public Button sign_up_btn;
    public Label error_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sign_in_btn.setOnAction(event->onSignIn());
        sign_up_btn.setOnAction(event->onSignUp());
        error_label.setVisible(false);
    }
    private void onSignIn(){
        Stage stage = (Stage) sign_in_btn.getScene().getWindow();
        String account = acc_field.getText();
        String password = pass_field.getText();
        if(Objects.equals(account.trim(), "")){
            error_label.setText("Please enter your username!");
            error_label.setVisible(true);
        }else if(Objects.equals(password.trim(), "")){
            error_label.setText("Please enter your password!");
            error_label.setVisible(true);
        }else{
            Model.getInstance().checkUserAccount(account, password);
            if(Model.getInstance().getUserLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showAppWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }else{
                error_label.setText("Your username or password is incorrect. Try again!");
                error_label.setVisible(true);
            }
        }

    }
    private void onSignUp(){
        Stage stage = (Stage) sign_up_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignUpWindow();
    }

}

package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpSceneController implements Initializable {
    public TextField acc_field;
    public TextField pass_field;
    public TextField confirm_pass_field;
    public FontAwesomeIcon go_back;
    public Button sign_in_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        go_back.setOnMouseClicked(mouseEvent -> goBack());
        sign_in_btn.setOnAction(event->signUp());
    }
    private void goBack(){
        Stage stage = (Stage) go_back.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignInWindow();
    }
    private void signUp(){
        Stage stage = (Stage) go_back.getScene().getWindow();
        String userName = acc_field.getText();
        String password = pass_field.getText();
        Model.getInstance().createUserAccount(userName, password, "", "", "", "", "", "");
        if(Model.getInstance().getUserLoginSuccessFlag()) {
            Model.getInstance().getViewFactory().closeStage(stage);
            Model.getInstance().getViewFactory().showAppWindow();
        }
    }
}

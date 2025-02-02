package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpSceneController implements Initializable {
    public TextField acc_field;
    public TextField pass_field;
    public TextField confirm_pass_field;
    public FontAwesomeIcon go_back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        go_back.setOnMouseClicked(mouseEvent -> goBack());
    }
    private void goBack(){
        Stage stage = (Stage) go_back.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignInWindow();
    }
}

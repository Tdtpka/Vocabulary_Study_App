package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Label tag_lbl_1;
    public Label tag_lbl_2;
    public Pane topic_1;
    public Pane topic_2;
    public Pane topic_3;
    public Pane topic_4;
    public TextField search_tag_field;
    public FontAwesomeIcon search_btn;
    public Button logout_btn;
    public Label username_label;
    public FontAwesomeIcon profile_btn;
    public Label date_label;
    public Button more_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        logout_btn.setOnAction(event->onLogout());
        LocalDate date = LocalDate.now();
        date_label.setText(date.toString());
    }
    private void onLogout(){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Bạn muốn thoát phiên đăng nhập?");
        alert.showAndWait();
        if(alert.getResult().getButtonData().isDefaultButton()) {
            Stage stage = (Stage) logout_btn.getScene().getWindow();
            Model.getInstance().getViewFactory().showSignInWindow();
            stage.close();
            Model.clearModel();
        }
    }
}

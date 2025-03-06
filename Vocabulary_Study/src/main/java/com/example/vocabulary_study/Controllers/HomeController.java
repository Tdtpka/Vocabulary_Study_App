package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Views.UserMenuOptions;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public TextField search_tag_field;
    public FontAwesomeIcon search_btn;
    public Button logout_btn;
    public Label username_label;
    public FontAwesomeIcon profile_btn;
    public Label date_label;
    public Button more_btn;
    public Label topic_label_1;
    public Label topic_label_2;
    public GridPane grid_pane_1;
    public GridPane grid_pane_2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        logout_btn.setOnAction(event->onLogout());
        LocalDate date = LocalDate.now();
        date_label.setText(date.toString());
        username_label.setText(Model.getUser().getUserName().get());
        more_btn.setOnAction(event->onMore());
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
    private void onMore(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.WORD);
    }
    private void updateUI(){

    }
}

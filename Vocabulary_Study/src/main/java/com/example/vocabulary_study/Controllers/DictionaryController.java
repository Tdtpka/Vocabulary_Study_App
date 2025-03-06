package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Models.UserDictionary;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {

    public static ObservableList<UserDictionary> userDictionaries = FXCollections.observableArrayList();
    public TextField search_tag_field;
    public FontAwesomeIcon search_btn;
    public Button logout_btn;
    public Label username_label;
    public FontAwesomeIcon profile_btn;
    public Label date_label;
    public Button create_dictionary_btn;
    public ScrollPane scroll_pane;
    public GridPane grid_pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        userDictionaries = Model.getUserDictionaries();
        logout_btn.setOnAction(event->onLogout());
        create_dictionary_btn.setOnAction(event->createNewDictionary());
        LocalDate date = LocalDate.now();
        date_label.setText(date.toString());
        username_label.setText(Model.getUser().getUserName().get());
        search_btn.setOnMouseClicked(event->onSearch());
        updateGridPane();
        userDictionaries.addListener((javafx.collections.ListChangeListener<UserDictionary>) change -> {
            updateGridPane(); // Gọi lại hàm cập nhật giao diện
        });
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
    private void onSearch(){
        Model.searchDictionary(Model.getUser().getUserID().get(), search_tag_field.getText());
    }
    private void createNewDictionary() {
        Model.getInstance().getViewFactory().showCreateDictionaryWindow();
    }
    private void updateGridPane() {
        grid_pane.getChildren().clear(); // Xóa các item cũ
        int row = 0, col = 0;
        for (UserDictionary userDictionary : userDictionaries) {
            if (col == 2) { // Giới hạn mỗi hàng 2 cột
                col = 0;
                row++;
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/dictionary_item.fxml"));
            try {
                Parent dictionaryItemNode = loader.load();
                DictionaryItemController dictionaryItemController = loader.getController();
                dictionaryItemController.setUserDictionary(userDictionary);
                grid_pane.add(dictionaryItemNode, col, row);
                col++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

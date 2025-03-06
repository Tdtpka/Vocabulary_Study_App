package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Models.Word;
import com.example.vocabulary_study.Views.WordCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateDictionaryController implements Initializable {
    public Button add_word_btn;
    public Button create_btn;
    public TextField dictionary_name_field;
    public TextField topic_field;
    public TextArea description_field;
    public static ObservableList<Word> list_word = FXCollections.observableArrayList();
    public ListView<Word> list_view;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        list_word.clear();
        add_word_btn.setOnAction(event->addNewWord());
        create_btn.setOnAction(event->createNewDictionary());
        list_view.setItems(list_word);
        list_view.setCellFactory(WordCellFactory::new);
    }
    private void addNewWord(){
        list_word.add(new Word("","",""));
    }
    private void createNewDictionary(){
        Model.getInstance().createUserDictionary(Model.getUser().getUserID().get(),Model.getUserDictionaries().size()+1, dictionary_name_field.getText(), topic_field.getText(), description_field.getText(), list_word);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Tạo từ điển thành công!");
        Optional<ButtonType> clickOK = alert.showAndWait();
        if(clickOK.isPresent() && clickOK.get() == ButtonType.OK){
            Stage stage = (Stage) add_word_btn.getScene().getWindow();
            stage.close();
        }
    }
}

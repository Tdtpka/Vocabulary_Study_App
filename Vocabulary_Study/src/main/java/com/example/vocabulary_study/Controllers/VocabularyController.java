package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Vocabulary;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VocabularyController implements Initializable {
    private final ListView<Vocabulary> list_view;
    private final Vocabulary vocabulary;
    public TextField word_field;
    public TextField meaning_field;
    public ChoiceBox<String> type_choicebox = new ChoiceBox<>();
    public Button delete_word_btn;
    public Button save_btn;
    public VocabularyController(Vocabulary vocabulary, ListView<Vocabulary> list_view){
        this.vocabulary = vocabulary;
        this.list_view = list_view;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        if (type_choicebox.getItems().isEmpty()) { // Đảm bảo chỉ thêm dữ liệu 1 lần
            type_choicebox.getItems().addAll("noun", "verb");
        }
        type_choicebox.setValue("noun");
        delete_word_btn.setOnAction(event->deleteWord());
        save_btn.setOnAction(event->saveWord());
        word_field.setText(vocabulary.wordProperty().get());
        type_choicebox.setValue(vocabulary.wordTypeProperty().get());
        meaning_field.setText(vocabulary.meaningProperty().get());
    }
    private void saveWord(){
        vocabulary.wordProperty().set(word_field.getText());
        vocabulary.wordTypeProperty().set(type_choicebox.getValue());
        vocabulary.meaningProperty().set(meaning_field.getText());
        list_view.getSelectionModel().select(vocabulary);
        int index = list_view.getSelectionModel().getSelectedIndex();
        list_view.getItems().set(index, vocabulary);
    }
    private void deleteWord(){
        list_view.getSelectionModel().select(vocabulary);
        int index = list_view.getSelectionModel().getSelectedIndex();
        list_view.getItems().remove(index);
    }
}

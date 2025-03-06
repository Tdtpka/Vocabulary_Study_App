package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Word;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class WordCellController implements Initializable {
    private final ListView<Word> list_view;
    private final Word word;
    public TextField word_field;
    public TextField meaning_field;
    public ChoiceBox<String> type_choicebox = new ChoiceBox<>();
    public Button delete_word_btn;
    public Button save_btn;
    public WordCellController(Word word, ListView<Word> list_view){
        this.word = word;
        this.list_view = list_view;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        if (type_choicebox.getItems().isEmpty()) { // Đảm bảo chỉ thêm dữ liệu 1 lần
            type_choicebox.getItems().addAll("noun", "verb");
        }
        delete_word_btn.setOnAction(event->deleteWord());
        save_btn.setOnAction(event->saveWord());
        word_field.setText(word.wordProperty().get());
        type_choicebox.setValue(word.typeProperty().get());
        meaning_field.setText(word.meaningProperty().get());
    }
    private void saveWord(){
        word.wordProperty().set(word_field.getText());
        word.typeProperty().set(type_choicebox.getValue());
        word.meaningProperty().set(meaning_field.getText());
        list_view.getSelectionModel().select(word);
        int index = list_view.getSelectionModel().getSelectedIndex();
        list_view.getItems().set(index, word);
    }
    private void deleteWord(){
        list_view.getSelectionModel().select(word);
        int index = list_view.getSelectionModel().getSelectedIndex();
        list_view.getItems().remove(index);
    }
}

package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Models.UserDictionary;
import com.example.vocabulary_study.Models.Vocabulary;
import com.example.vocabulary_study.Models.Word;
import com.example.vocabulary_study.Views.VocabularyCellFactory;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class DictionaryWordController implements Initializable {

    public TextField search_word_field;
    public FontAwesomeIcon search_btn;
    public Button logout_btn;
    public FontAwesomeIcon profile_btn;
    public Label date_label;
    public Label dictionary_name_label;
    public Button new_word_btn;
    public ListView<Vocabulary> list_view;
    public Label username_label;
    public Button save_btn;
    public Label topic_label;
    private ObservableList<Vocabulary> vocabularies = FXCollections.observableArrayList();
    private int dictionaryID = -1;
    private String description = "";
    private int numberOfSavedWord = -1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        vocabularies.clear();
        UserDictionary dictionary = Model.getDictionary();
        dictionaryID = dictionary.dictionaryIDProperty().get();
        topic_label.setText(dictionary.topicProperty().get());
        description = dictionary.descriptionProperty().get();
        vocabularies = Model.getDictionaryVocabularies(dictionary.dictionaryIDProperty().get());
        numberOfSavedWord = vocabularies.size();
        date_label.setText(LocalDate.now().toString());
        username_label.setText(Model.getUser().getUserName().get());
        dictionary_name_label.setText(dictionary.dictionaryNameProperty().get());
        list_view.setItems(vocabularies);
        list_view.setCellFactory(VocabularyCellFactory::new);
        new_word_btn.setOnAction(event->addNewWord());
        save_btn.setOnAction(event->updateDictionary());
    }

    private void updateDictionary() {
        Model.getInstance().updateUserDictionary(Model.getUser().getUserID().get(), dictionaryID, dictionary_name_label.getText(), topic_label.getText(),description, vocabularies, numberOfSavedWord);
        numberOfSavedWord = vocabularies.size();
        Alert alert  = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Cập nhật từ điển thành công!");
        alert.showAndWait();
    }

    private void addNewWord(){
        vocabularies.add(new Vocabulary(Model.getUser().getUserID().get(), vocabularies.size()+1, dictionaryID, "","",""));
    }
}

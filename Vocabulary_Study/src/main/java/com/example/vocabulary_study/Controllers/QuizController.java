package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Models.UserDictionary;
import com.example.vocabulary_study.Views.DictionaryCellFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class QuizController implements Initializable {
    public ListView<UserDictionary> dictionary_listview;
    public Button logout_btn;
    public Label username_label;
    public FontAwesomeIcon profile_btn;
    public Label date_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        date_label.setText(LocalDate.now().toString());
        dictionary_listview.setItems(Model.getUserDictionaries());
        dictionary_listview.setCellFactory(list_quiz-> new DictionaryCellFactory());
    }

}

package com.example.vocabulary_study.Controllers;

import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Models.UserDictionary;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryCellController implements Initializable {
    private final UserDictionary userDictionary;
    public Pane pane;
    public Label dictionary_name_label;
    public Label number_correct_label;
    public CheckBox completed_checkbox;
    public DictionaryCellController(UserDictionary userDictionary){
        this.userDictionary = userDictionary;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        dictionary_name_label.setText(userDictionary.dictionaryNameProperty().get());
        if(Model.getQuizResults(userDictionary.dictionaryIDProperty().get())!=-1){
            completed_checkbox.selectedProperty().set(true);
            number_correct_label.setText(Model.getQuizResults(userDictionary.dictionaryIDProperty().get())+"/"+userDictionary.totalWordProperty().get());
        }else {
            completed_checkbox.selectedProperty().set(false);
            number_correct_label.setVisible(false);
        }
        pane.setOnMousePressed(event->{
            Model.getInstance().getViewFactory().showQuizDataWindow(userDictionary);
        });
    }
}

package com.example.vocabulary_study.Controllers;
import com.example.vocabulary_study.Models.Model;
import com.example.vocabulary_study.Views.UserMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import com.example.vocabulary_study.Models.UserDictionary;

public class DictionaryItemController implements Initializable {

    public Label total_word_label;
    public Label description_label;
    public Pane dictionary_item;
    private UserDictionary userDictionary;
    public Label dictionary_name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        updateItem();
        dictionary_item.setOnMouseClicked(event->onItem());
    }
    public void setUserDictionary(UserDictionary userDictionary){
        this.userDictionary = userDictionary;
        updateItem();
    }
    private void onItem(){
        Model.setDictionary(userDictionary);
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.DICTIONARY);
    }
    private void updateItem(){
        if(userDictionary == null) return;
        dictionary_name.setText(userDictionary.dictionaryNameProperty().get());
        total_word_label.setText(userDictionary.totalWordProperty().getValue().toString()+" words");
        description_label.setText(userDictionary.descriptionProperty().get());
    }
}

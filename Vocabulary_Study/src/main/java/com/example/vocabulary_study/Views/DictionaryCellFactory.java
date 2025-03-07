package com.example.vocabulary_study.Views;

import com.example.vocabulary_study.Controllers.DictionaryCellController;
import com.example.vocabulary_study.Models.UserDictionary;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class DictionaryCellFactory extends ListCell<UserDictionary> {
    @Override
    protected void updateItem(UserDictionary userDictionary, boolean empty){
        super.updateItem(userDictionary, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/dictionary_cell.fxml"));
            DictionaryCellController dictionaryCellController = new DictionaryCellController(userDictionary);
            loader.setController(dictionaryCellController);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

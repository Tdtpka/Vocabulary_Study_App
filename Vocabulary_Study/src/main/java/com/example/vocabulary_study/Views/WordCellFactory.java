package com.example.vocabulary_study.Views;

import com.example.vocabulary_study.Controllers.WordCellController;
import com.example.vocabulary_study.Models.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

public class WordCellFactory extends ListCell<Word> {
    private final ListView<Word> list_view;

    public WordCellFactory(ListView<Word> list_view) {
        this.list_view = list_view;
    }

    @Override
    protected void updateItem(Word word, boolean empty){
        super.updateItem(word, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/word_cell.fxml"));
            WordCellController cellController = new WordCellController(word, list_view);
            loader.setController(cellController);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

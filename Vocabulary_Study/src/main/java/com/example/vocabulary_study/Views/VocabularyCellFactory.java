package com.example.vocabulary_study.Views;

import com.example.vocabulary_study.Controllers.VocabularyController;
import com.example.vocabulary_study.Controllers.WordCellController;
import com.example.vocabulary_study.Models.Vocabulary;
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

public class VocabularyCellFactory extends ListCell<Vocabulary> {
    private final ListView<Vocabulary> list_view;

    public VocabularyCellFactory(ListView<Vocabulary> list_view) {
        this.list_view = list_view;
    }

    @Override
    protected void updateItem(Vocabulary vocabulary, boolean empty){
        super.updateItem(vocabulary, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/word_cell.fxml"));
            VocabularyController vocabularyController = new VocabularyController(vocabulary, list_view);
            loader.setController(vocabularyController);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

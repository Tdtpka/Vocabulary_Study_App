package com.example.vocabulary_study.Views;

import com.example.vocabulary_study.Controllers.QuizCellController;
import com.example.vocabulary_study.Models.Quiz;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class QuizCellFactory extends ListCell<Quiz> {
    @Override
    protected void updateItem(Quiz quiz, boolean empty){
        super.updateItem(quiz, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/vocabulary_study/Fxml/quiz_cell.fxml"));
            QuizCellController quizCellController = new QuizCellController(quiz);
            loader.setController(quizCellController);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

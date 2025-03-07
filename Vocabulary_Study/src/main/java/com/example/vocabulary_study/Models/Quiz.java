package com.example.vocabulary_study.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Quiz {
    private final Vocabulary vocabulary;
    private final StringProperty answer;
    private final BooleanProperty isCorrect;

    public Quiz(Vocabulary vocabulary, boolean isCorrect){
        this.vocabulary = vocabulary;
        this.isCorrect = new SimpleBooleanProperty(this, "isCorrect", isCorrect);
        this.answer = new SimpleStringProperty(this, "answer", "");
    }
    public Vocabulary getVocabulary(){
        return vocabulary;
    }
    public BooleanProperty isCorrectProperty(){
        return isCorrect;
    }
    public StringProperty answerProperty(){
        return answer;
    }
}

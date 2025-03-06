package com.example.vocabulary_study.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vocabulary {
    private IntegerProperty userID;
    private IntegerProperty vocabularyID;
    private IntegerProperty dictionaryID;
    private StringProperty word;
    private StringProperty wordType;
    private StringProperty meaning;

    public Vocabulary(int userID, int vocabID, int dictionaryID, String word, String wordType, String meaning){
        this.userID = new SimpleIntegerProperty(this, "User ID", userID);
        this.vocabularyID = new SimpleIntegerProperty(this, "Vocabulary ID", vocabID);
        this.dictionaryID = new SimpleIntegerProperty(this, "Dictionary ID", dictionaryID);
        this.word = new SimpleStringProperty(this, "Word", word);
        this.wordType = new SimpleStringProperty(this, "Word Type", word);
        this.meaning = new SimpleStringProperty(this, "Meaning", meaning);
    }
    public IntegerProperty userIDProperty(){
        return userID;
    }

    public IntegerProperty vocabIDProperty() {
        return vocabularyID;
    }

    public IntegerProperty dictionaryIDProperty() {
        return dictionaryID;
    }

    public StringProperty wordProperty() {
        return word;
    }

    public StringProperty wordTypeProperty() {
        return wordType;
    }

    public StringProperty meaningProperty() {
        return meaning;
    }
}

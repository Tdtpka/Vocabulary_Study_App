package com.example.vocabulary_study.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DefaultVocabulary {
    private final IntegerProperty vocabularyID;
    private final IntegerProperty dictionaryID;
    private final StringProperty word;
    private final StringProperty wordType;
    private final StringProperty meaning;

    public DefaultVocabulary(int vocabID, int dictionaryID, String word, String wordType, String meaning){
        this.vocabularyID = new SimpleIntegerProperty(this, "vocabID", vocabID);
        this.dictionaryID = new SimpleIntegerProperty(this, "Dictionary ID", dictionaryID);
        this.word = new SimpleStringProperty(this, "Word", word);
        this.wordType = new SimpleStringProperty(this, "Word Type", wordType);
        this.meaning = new SimpleStringProperty(this, "Meaning", meaning);
    }


    public IntegerProperty vocabularyIDProperty() {
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

package com.example.vocabulary_study.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Word {
    private final StringProperty word;
    private final StringProperty type;
    private final StringProperty meaning;

    public Word(String word, String type, String meaning){
        this.word = new SimpleStringProperty(this, "Word", word);
        this.type = new SimpleStringProperty(this, "Type", type);
        this.meaning = new SimpleStringProperty(this, "Meaning", meaning);
    }

    public StringProperty wordProperty(){
        return word;
    }
    public StringProperty typeProperty(){
        return type;
    }
    public StringProperty meaningProperty(){
        return meaning;
    }
}

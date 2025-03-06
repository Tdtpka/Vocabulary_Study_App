package com.example.vocabulary_study.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDictionary {
    private final IntegerProperty userID;
    private final IntegerProperty dictionaryID;
    private final StringProperty dictionaryName;
    private final StringProperty topic;
    private final IntegerProperty totalWord;
    private final StringProperty description;

    public UserDictionary(int userID, int dictionaryID, String dictionaryName, String topic, int totalWord, String description){
        this.userID = new SimpleIntegerProperty(this, "User ID", userID);
        this.dictionaryID = new SimpleIntegerProperty(this, "Dictionary ID", dictionaryID);
        this.dictionaryName = new SimpleStringProperty(this, "Dictionary Name", dictionaryName);
        this.topic = new SimpleStringProperty(this, "Topic", topic);
        this.totalWord = new SimpleIntegerProperty(this, "Total Word", totalWord);
        this.description = new SimpleStringProperty(this, "Description", description);
    }

    public IntegerProperty userIDProperty() {
        return userID;
    }

    public IntegerProperty dictionaryIDProperty() {
        return dictionaryID;
    }

    public StringProperty dictionaryNameProperty() {
        return dictionaryName;
    }

    public StringProperty topicProperty() {
        return topic;
    }

    public IntegerProperty totalWordProperty() {
        return totalWord;
    }

    public StringProperty descriptionProperty(){ return description; }
}

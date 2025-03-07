package com.example.vocabulary_study.Models;

import javafx.beans.property.*;

public class UserDictionary {
    private final IntegerProperty userID;
    private final IntegerProperty dictionaryID;
    private final StringProperty dictionaryName;
    private final StringProperty topic;
    private final IntegerProperty totalWord;
    private final StringProperty description;
    private final BooleanProperty completed;

    public UserDictionary(int userID, int dictionaryID, String dictionaryName, String topic, int totalWord, String description, boolean completed){
        this.userID = new SimpleIntegerProperty(this, "User ID", userID);
        this.dictionaryID = new SimpleIntegerProperty(this, "Dictionary ID", dictionaryID);
        this.dictionaryName = new SimpleStringProperty(this, "Dictionary Name", dictionaryName);
        this.topic = new SimpleStringProperty(this, "Topic", topic);
        this.totalWord = new SimpleIntegerProperty(this, "Total Word", totalWord);
        this.description = new SimpleStringProperty(this, "Description", description);
        this.completed = new SimpleBooleanProperty(this, "completed", completed);
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

    public BooleanProperty completedProperty(){ return completed; }
}

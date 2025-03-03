package com.example.vocabulary_study.Models;

public class DeletedWord {
    private int id;
    private int wordID;
    private int userID;

    public DeletedWord(int id, int wordID, int userID) {
        this.id = id;
        this.wordID = wordID;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public int getWordID() {
        return wordID;
    }

    public int getUserID() {
        return userID;
    }
}

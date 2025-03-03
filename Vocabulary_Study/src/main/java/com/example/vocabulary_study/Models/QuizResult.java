package com.example.vocabulary_study.Models;

public class QuizResult {
    private int resultID;
    private int userID;
    private int knownWord;

    public QuizResult(int resultID, int userID, int knownWord) {
        this.resultID = resultID;
        this.userID = userID;
        this.knownWord = knownWord;
    }

    public int getResultID() {
        return resultID;
    }

    public int getUserID() {
        return userID;
    }

    public int getKnownWord() {
        return knownWord;
    }
}

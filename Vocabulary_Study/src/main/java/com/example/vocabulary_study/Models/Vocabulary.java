package com.example.vocabulary_study.Models;

public class Vocabulary {
    private int userID;
    private int vocabularyID;
    private String topic;
    private String word;
    private String wordType;
    private String meaning;

    public Vocabulary(int userID, int vocabID, String topic, String word, String wordType, String meaning){
        this.userID = userID;
        this.vocabularyID = vocabID;
        this.topic = topic;
        this.word = word;
        this.wordType = wordType;
        this.meaning = meaning;
    }
    public int getUserID(){
        return userID;
    }

    public int getVocabID() {
        return vocabularyID;
    }

    public String getTopic() {
        return topic;
    }

    public String getWord() {
        return word;
    }

    public String getWordType() {
        return wordType;
    }

    public String getMeaning() {
        return meaning;
    }
}

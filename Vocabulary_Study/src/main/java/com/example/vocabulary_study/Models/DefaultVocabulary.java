package com.example.vocabulary_study.Models;

public class DefaultVocabulary {
    private int vocabularyID;
    private String topic;
    private String word;
    private String wordType;
    private String meaning;

    public DefaultVocabulary(int vocabID, String topic, String word, String wordType, String meaning){
        this.vocabularyID = vocabID;
        this.topic = topic;
        this.word = word;
        this.wordType = wordType;
        this.meaning = meaning;
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

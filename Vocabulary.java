package com.example.vocabulary_study;

public class Vocabulary {
    private int userId;
    private String topic;
    private String word;
    private String wordType;
    private String meaning;

    public Vocabulary(int userId, String topic, String word, String wordType, String meaning) {
        this.userId = userId;
        this.topic = topic;
        this.word = word;
        this.wordType = wordType;
        this.meaning = meaning;
    }

    public int getUserId() { return userId; }
    public String getTopic() { return topic; }
    public String getWord() { return word; }
    public String getWordType() { return wordType; }
    public String getMeaning() { return meaning; }
}

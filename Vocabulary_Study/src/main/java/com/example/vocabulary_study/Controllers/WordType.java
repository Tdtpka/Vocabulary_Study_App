package com.example.vocabulary_study.Controllers;

public enum WordType {
    NOUN("noun"),
    PRONOUN("pronoun"),
    ADJECTIVE("adjective"),
    VERB("verb"),
    ADVERB("adverb"),
    PREPOSITION("preposition"),
    CONJUNCTION("conjunction");
    private final String label;

    WordType(String label){
        this.label = label;
    }
    public String getLabel(){
        return label;
    }
}

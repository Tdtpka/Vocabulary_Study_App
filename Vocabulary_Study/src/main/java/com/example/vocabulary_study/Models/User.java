package com.example.vocabulary_study.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final IntegerProperty userID;
    private final StringProperty userName;
    private final StringProperty password;
    private final StringProperty questionA;
    private final StringProperty questionB;
    private final StringProperty questionC;
    private final StringProperty answerA;
    private final StringProperty answerB;
    private final StringProperty answerC;

    public User(int userID, String userName, String password, String questionA, String questionB, String questionC, String answerA, String answerB, String answerC){
        this.userID = new SimpleIntegerProperty(this, "User ID", userID);
        this.userName = new SimpleStringProperty(this, "User Name", userName);
        this.password = new SimpleStringProperty(this, "Password", password);
        this.questionA = new SimpleStringProperty(this, "Question A", questionA);
        this.questionB = new SimpleStringProperty(this, "Question B", questionB);
        this.questionC = new SimpleStringProperty(this, "Question C", questionC);
        this.answerA = new SimpleStringProperty(this, "Answer A", answerA);
        this. answerB = new SimpleStringProperty(this, "Answer B", answerB);
        this.answerC = new SimpleStringProperty(this, "Answer C", answerC);
    }

    public IntegerProperty getUserID() {
        return userID;
    }

    public StringProperty getUserName() {
        return userName;
    }

    public StringProperty getPassword() {
        return password;
    }

    public StringProperty getQuestionA() {
        return questionA;
    }

    public StringProperty getQuestionB() {
        return questionB;
    }

    public StringProperty getQuestionC() {
        return questionC;
    }

    public StringProperty getAnswerA() {
        return answerA;
    }

    public StringProperty getAnswerB() {
        return answerB;
    }

    public StringProperty getAnswerC() {
        return answerC;
    }
}

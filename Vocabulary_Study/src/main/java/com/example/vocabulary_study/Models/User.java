package com.example.vocabulary_study.Models;

import com.mysql.cj.xdevapi.Client;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String questionA;
    private String questionB;
    private String questionC;
    private String answerA;
    private String answerB;
    private String answerC;

    public User(int userID, String userName, String password, String questionA, String questionB, String questionC, String answerA, String answerB, String answerC){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.questionA = questionA;
        this.questionB = questionB;
        this.questionC = questionC;
        this.answerA = answerA;
        this. answerB = answerB;
        this.answerC = answerC;
    }
    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    public String getQuestionA() {
        return questionA;
    }

    public String getQuestionB() {
        return questionB;
    }

    public String getQuestionC() {
        return questionC;
    }
    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }


}

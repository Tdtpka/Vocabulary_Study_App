package com.example.vocabulary_study.Models;

import com.example.vocabulary_study.DbConnect;
import com.example.vocabulary_study.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private static ObservableList<DefaultVocabulary> defaultVocabularies;
    private static ObservableList<Vocabulary> vocabularies;
    private boolean userLoginSuccessFlag = false;
    private static User user;
    //Model section
    private Model(){
        this.viewFactory = new ViewFactory();
        user = new User(0,"","","","","","","","");
        defaultVocabularies = FXCollections.observableArrayList();
        vocabularies = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance(){
        if(model==null){
            model = new Model();
        }
        return model;
    }
    public static void clearModel(){
        model = null;
    }
    //ViewFactory
    public ViewFactory getViewFactory() {
        return viewFactory;
    }
    //User section
    //Login
    public boolean getUserLoginSuccessFlag(){
        return this.userLoginSuccessFlag;
    }
    public void setUserLoginSuccessFlag(boolean userLoginSuccessFlag) {
        this.userLoginSuccessFlag = userLoginSuccessFlag;
    }
    public static User getUser(){
        return user;
    }
    public void checkUserAccount(String account, String password){
        ResultSet resultSet = DatabaseDriver.getUserData(account, password);
        try{
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                user.getUserID().set(resultSet.getInt("user_id"));
                user.getUserName().set(resultSet.getString("username"));
                user.getPassword().set(resultSet.getString("password"));
                user.getQuestionA().set(resultSet.getString("security_question_1"));
                user.getQuestionB().set(resultSet.getString("security_question_2"));
                user.getQuestionC().set(resultSet.getString("security_question_3"));
                user.getAnswerA().set(resultSet.getString("security_answer_1"));
                user.getAnswerB().set(resultSet.getString("security_answer_2"));
                user.getAnswerC().set(resultSet.getString("security_answer_3"));
                setUserLoginSuccessFlag(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createUserAccount(String userName, String password, String questionA , String questionB, String questionC, String answerA, String answerB, String answerC){
        DatabaseDriver.createNewUser(userName, password, questionA, questionB, questionC, answerA, answerB, answerC);
        checkUserAccount(userName, password);
    }
//    public void setVocabularies(){
//        if(userLoginSuccessFlag){
//            ResultSet resultSet = DatabaseDriver.getVocabularyData(user.getUserID().get());
//            try{
//                while (resultSet.next()){
//                    Vocabulary vocabulary = new Vocabulary();
//
//                }
//            }
//        }
//    }
    public void setDefaultVocabularies(){
        ResultSet resultSet = DatabaseDriver.getDefaultVocabularyData();
        try {
            while (resultSet.next()){
                DefaultVocabulary defaultVocabulary = new DefaultVocabulary(0, "", "", "", "");
                defaultVocabulary.vocabularyIDProperty().set(resultSet.getInt("vocab_id"));
                defaultVocabulary.topicProperty().set(resultSet.getString("topic"));
                defaultVocabulary.wordProperty().set(resultSet.getString("word"));
                defaultVocabulary.wordTypeProperty().set(resultSet.getString("word_type"));
                defaultVocabulary.meaningProperty().set(resultSet.getString("meaning"));
                defaultVocabularies.addFirst(defaultVocabulary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<DefaultVocabulary> getDefaultVocabularies(){
        return defaultVocabularies;
    }

}

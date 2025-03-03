package com.example.vocabulary_study.Models;

import com.example.vocabulary_study.DbConnect;
import com.example.vocabulary_study.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private boolean userLoginSuccessFlag = false;
    private static User user;
    //Model section
    private Model(){
        this.viewFactory = new ViewFactory();
        user = new User(0,"","","","","","","","");
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

}

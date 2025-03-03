package com.example.vocabulary_study.Models;

import com.example.vocabulary_study.DbConnect;

import java.sql.*;

public class DatabaseDriver {
    public static ResultSet getUserData(String account, String password){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = DbConnect.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM users WHERE username='"+account+"' AND password='"+password+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static void createNewUser(String userName, String password, String questionA , String questionB, String questionC, String answerA, String answerB, String answerC){
        PreparedStatement preparedStatement;
        try{
            preparedStatement = DbConnect.getConnection().prepareStatement("INSERT INTO users (username, password, security_question_1, security_answer_1, security_question_2, security_answer_2, security_question_3, security_answer_3) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, questionA);
            preparedStatement.setString(4,answerA);
            preparedStatement.setString(5, questionB);
            preparedStatement.setString(6, answerB);
            preparedStatement.setString(7, questionC);
            preparedStatement.setString(8, answerC);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

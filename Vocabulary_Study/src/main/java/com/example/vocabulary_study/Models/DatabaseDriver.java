package com.example.vocabulary_study.Models;

import com.example.vocabulary_study.DbConnect;
import javafx.collections.ObservableList;

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
            preparedStatement.setString(4, answerA);
            preparedStatement.setString(5, questionB);
            preparedStatement.setString(6, answerB);
            preparedStatement.setString(7, questionC);
            preparedStatement.setString(8, answerC);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ResultSet getVocabularyData(int userID, int dictionaryID){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = DbConnect.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM vocabulary WHERE user_id = '"+userID+"' AND dictionary_id = '"+dictionaryID+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
    public static ResultSet getDefaultVocabularyData(){
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = DbConnect.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM defaultvocabulary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getUserDictionaryData(int userID) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = DbConnect.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Dictionary WHERE user_id = '"+userID+"'");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSet getDefaultDictionaryData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = DbConnect.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM DefaultDictionary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void createUserDictionary(int userID, int dictionaryID, String dictionaryName, String topic, String description, ObservableList<Word> list_word) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DbConnect.getConnection().prepareStatement("INSERT INTO Dictionary (dictionary_id, dictionary_name, topic, user_id, total_word, dic_description) VALUES (?,?,?,?,?,?)");
            preparedStatement.setInt(1, dictionaryID);
            preparedStatement.setString(2, dictionaryName);
            preparedStatement.setString(3, topic);
            preparedStatement.setInt(4, userID);
            preparedStatement.setInt(5, list_word.size());
            preparedStatement.setString(6, description);
            preparedStatement.execute();
            for (int i = 0; i < list_word.size(); i++) {
                preparedStatement = DbConnect.getConnection().prepareStatement("INSERT INTO Vocabulary (vocab_id, user_id, dictionary_id, word, word_type, meaning) VALUES (?,?,?,?,?,?)");
                preparedStatement.setInt(1, i+1);
                preparedStatement.setInt(2, userID);
                preparedStatement.setInt(3, dictionaryID);
                preparedStatement.setString(4, list_word.get(i).wordProperty().get());
                preparedStatement.setString(5, list_word.get(i).typeProperty().get());
                preparedStatement.setString(6, list_word.get(i).meaningProperty().get());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateUserDictionary(int userID, int dictionaryID, String dictionaryName, String topic, String description, ObservableList<Vocabulary> vocabularies, int savedWord) {
        PreparedStatement preparedStatement;
        try{
            preparedStatement = DbConnect.getConnection().prepareStatement("UPDATE Dictionary SET dictionary_name = ?, topic = ?, total_word = ?, dic_description = ? WHERE user_id = '"+userID+"' AND dictionary_id = '"+dictionaryID+"'");
            preparedStatement.setString(1, dictionaryName);
            preparedStatement.setString(2, topic);
            preparedStatement.setInt(3, vocabularies.size());
            preparedStatement.setString(4, description);
            preparedStatement.execute();
            for(int i=0; i<vocabularies.size(); i++){
                if(i<savedWord){
                    preparedStatement = DbConnect.getConnection().prepareStatement("UPDATE Vocabulary SET word = ?, word_type = ?, meaning = ? WHERE user_id = '"+userID+"' AND dictionary_id = '"+dictionaryID+"' AND vocab_id = '"+vocabularies.get(i).vocabIDProperty().get()+"'");
                    preparedStatement.setString(1, vocabularies.get(i).wordProperty().get());
                    preparedStatement.setString(2, vocabularies.get(i).wordProperty().get());
                    preparedStatement.setString(3, vocabularies.get(i).meaningProperty().get());
                    preparedStatement.execute();
                }else{
                    preparedStatement = DbConnect.getConnection().prepareStatement("INSERT INTO Vocabulary (vocab_id, user_id, dictionary_id, word, word_type, meaning) VALUES (?,?,?,?,?,?)");
                    preparedStatement.setInt(1, i+1);
                    preparedStatement.setInt(2, userID);
                    preparedStatement.setInt(3, dictionaryID);
                    preparedStatement.setString(4, vocabularies.get(i).wordProperty().get());
                    preparedStatement.setString(5, vocabularies.get(i).wordTypeProperty().get());
                    preparedStatement.setString(6, vocabularies.get(i).meaningProperty().get());
                    preparedStatement.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ResultSet searchUserDictionary(int userID, String dictionaryName) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = DbConnect.getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Dictionary WHERE user_id = '"+userID+"' AND dictionary_name LIKE '%"+dictionaryName+"%'");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }


}

package com.example.vocabulary_study.Models;

import com.example.vocabulary_study.DbConnect;
import com.example.vocabulary_study.Views.UserMenuOptions;
import com.example.vocabulary_study.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private static ObservableList<DefaultDictionary> defaultDictionaries;
    private static ObservableList<DefaultVocabulary> defaultVocabularies;
    private static ObservableList<UserDictionary> userDictionaries;
    private static ObservableList<Vocabulary> vocabularies;
    private static ObservableList<Vocabulary> deletedVocabularies;
    private static UserDictionary userDictionary;
    private boolean userLoginSuccessFlag = false;
    private static User user;
    //Model section
    private Model(){
        this.viewFactory = new ViewFactory();
        user = new User(0,"","","","","","","","");
        defaultVocabularies = FXCollections.observableArrayList();
        vocabularies = FXCollections.observableArrayList();
        deletedVocabularies = FXCollections.observableArrayList();
        userDictionaries = FXCollections.observableArrayList();
        defaultDictionaries = FXCollections.observableArrayList();
        userDictionary = new UserDictionary(0,0,"","",0,"", false);
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
                setUserDictionaries(user.getUserID().get());
                setDefaultDictionaries();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setDefaultDictionaries() {
        ResultSet resultSet = DatabaseDriver.getDefaultDictionaryData();
        try{
            while (resultSet.next()){
                DefaultDictionary defaultDictionary = new DefaultDictionary(0, "","", 0);
                defaultDictionary.dictionaryIDProperty().set(resultSet.getInt("dictionary_id"));
                defaultDictionary.dictionaryNameProperty().set(resultSet.getString("dictionary_name"));
                defaultDictionary.topicProperty().set(resultSet.getString("topic"));
                defaultDictionary.totalWordProperty().set(resultSet.getInt("total_word"));
                defaultDictionaries.addFirst(defaultDictionary);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static ObservableList<DefaultDictionary> getDefaultDictionaries(){
        return defaultDictionaries;
    }

    public void createUserAccount(String userName, String password, String questionA , String questionB, String questionC, String answerA, String answerB, String answerC){
        DatabaseDriver.createNewUser(userName, password, questionA, questionB, questionC, answerA, answerB, answerC);
        checkUserAccount(userName, password);
    }

    public void setDefaultVocabularies(){
        ResultSet resultSet = DatabaseDriver.getDefaultVocabularyData();
        try {
            while (resultSet.next()){
                DefaultVocabulary defaultVocabulary = new DefaultVocabulary(0, 0, "", "", "");
                defaultVocabulary.vocabularyIDProperty().set(resultSet.getInt("vocab_id"));
                defaultVocabulary.dictionaryIDProperty().set(resultSet.getInt("dictionary_id"));
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

    public void createUserDictionary(int userID, int dictionaryID, String dictionaryName, String topic, String description, ObservableList<Word> list_word){
        DatabaseDriver.createUserDictionary(userID, dictionaryID, dictionaryName, topic, description, list_word);
        setUserDictionaries(userID);
    }
    public void updateUserDictionary(int userID, int dictionaryID, String dictionaryName, String topic, String description, ObservableList<Vocabulary> vocabularies, int savedWord) {
        DatabaseDriver.updateUserDictionary(userID, dictionaryID, dictionaryName, topic, description, vocabularies, savedWord);
        setUserDictionaries(userID);
    }
    public static void setUserDictionaries(int userID){
        userDictionaries.clear();
        ResultSet resultSet = DatabaseDriver.getUserDictionaryData(userID);
        try {
            while (resultSet.next()){
                UserDictionary userDictionary = new UserDictionary(0,0,"","",0,"", false);
                userDictionary.userIDProperty().set(resultSet.getInt("user_id"));
                userDictionary.dictionaryIDProperty().set(resultSet.getInt("dictionary_id"));
                userDictionary.dictionaryNameProperty().set(resultSet.getString("dictionary_name"));
                userDictionary.topicProperty().set(resultSet.getString("topic"));
                userDictionary.totalWordProperty().set(resultSet.getInt("total_word"));
                userDictionary.descriptionProperty().set(resultSet.getString("dic_description"));
                userDictionary.completedProperty().set(resultSet.getBoolean("is_completed"));
                userDictionaries.addFirst(userDictionary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void searchDictionary(int userID, String dictionaryName){
        userDictionaries.clear();
        ResultSet resultSet = DatabaseDriver.searchUserDictionary(userID, dictionaryName);
        try {
            while (resultSet.next()){
                UserDictionary userDictionary = new UserDictionary(0,0,"","",0,"", false);
                userDictionary.userIDProperty().set(resultSet.getInt("user_id"));
                userDictionary.dictionaryIDProperty().set(resultSet.getInt("dictionary_id"));
                userDictionary.dictionaryNameProperty().set(resultSet.getString("dictionary_name"));
                userDictionary.topicProperty().set(resultSet.getString("topic"));
                userDictionary.totalWordProperty().set(resultSet.getInt("total_word"));
                userDictionary.descriptionProperty().set(resultSet.getString("dic_description"));
                userDictionary.completedProperty().set(resultSet.getBoolean("is_completed"));
                userDictionaries.addFirst(userDictionary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<UserDictionary> getUserDictionaries(){
        return userDictionaries;
    }
    public static void setDictionary(UserDictionary dictionary){
        userDictionary = dictionary;
    }
    public static UserDictionary getDictionary(){
        return userDictionary;
    }
    public static ObservableList<Vocabulary> getDictionaryVocabularies(int dictionaryID){
        vocabularies.clear();
        ResultSet resultSet = DatabaseDriver.getVocabularyData(user.getUserID().get(), dictionaryID);
        try {
            while (resultSet.next()){
                Vocabulary vocabulary = new Vocabulary(0,0,0,"","","");
                vocabulary.userIDProperty().set(resultSet.getInt("user_id"));
                vocabulary.dictionaryIDProperty().set(resultSet.getInt("dictionary_id"));
                vocabulary.vocabIDProperty().set(resultSet.getInt("vocab_id"));
                vocabulary.wordProperty().set(resultSet.getString("word"));
                vocabulary.wordTypeProperty().set(resultSet.getString("word_type"));
                vocabulary.meaningProperty().set(resultSet.getString("meaning"));
                vocabularies.addFirst(vocabulary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vocabularies;
    }
    public static ObservableList<Vocabulary> getDeletedVocabularies(){
        return deletedVocabularies;
    }


    public static void updateQuizResults(ObservableList<Quiz> listQuiz, int dictionaryID) {
        DatabaseDriver.updateQuizResults(listQuiz, dictionaryID);
    }

    public static int getQuizResults(int dictionaryID){
        ResultSet resultSet = DatabaseDriver.getQuizResult(dictionaryID);
        int correct = 0;
        boolean isCompleted= false;
        try {
            while (resultSet.next()){
                isCompleted = true;
                if(resultSet.getBoolean("is_correct")) correct++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(isCompleted) return correct;
        return -1;
    }
}

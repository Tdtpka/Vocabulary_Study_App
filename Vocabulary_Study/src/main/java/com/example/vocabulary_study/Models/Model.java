package com.example.vocabulary_study.Models;

import com.example.vocabulary_study.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private boolean userLoginSuccessFlag = false;
    private static User user;
    //Model section
    private Model(){
        viewFactory = new ViewFactory();
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

}

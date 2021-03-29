package com.univem.appunivem.repositories;

import android.util.Log;

import com.univem.appunivem.model.User;
import com.univem.appunivem.rest.UserRest;

public class Repositories {

    private static Repositories instance;

    public static Repositories getInstance(){
        if(instance == null){
            instance = new Repositories();
        }
        return instance;
    }

    public User login(String username, String password){
        return UserRest.getInstance().login(username,password).getValue();
    }
}

package com.univem.appunivem.model;

public class User {
    public String username;
    public String password;
    public String token;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

package com.example.loseit_androidapp;

public class UserModel {

    // create an oncreate

    private String name;
    private String email;
    private String password;
    private String weight;
    private String height;
    private String goalweight;
    private String level;
    private String fitnesslevel;


    // constructor that only takes email and password
    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }





}

package com.example.login_form;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserToken {

    private String username;
    private String password;

    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("msg")
    @Expose
    public String msg;



}


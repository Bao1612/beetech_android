package com.example.login_form;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserToken {
    @SerializedName("token")
    @Expose
    public String token;

}


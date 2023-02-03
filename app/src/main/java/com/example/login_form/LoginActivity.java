package com.example.login_form;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginActivity {
    @SerializedName("username")
    @Expose
    public String username;

    @SerializedName("password")
    @Expose
    public String password;

}



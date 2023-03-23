package com.example.login_form;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stores {

    @SerializedName("name")
    private String nameStore;

    public String getNameStore() {
        return nameStore;
    }
}

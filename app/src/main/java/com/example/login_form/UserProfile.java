package com.example.login_form;

import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("body")
    private String name;
    private String phone;
    private String address;

    public UserProfile(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}

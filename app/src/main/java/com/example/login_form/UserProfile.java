package com.example.login_form;

import com.google.gson.annotations.SerializedName;

public class UserProfile extends UserToken {

    private String name;
    private String phone;
    private String address;
    private String internalId;

    public String getInternalID() {return internalId;}
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

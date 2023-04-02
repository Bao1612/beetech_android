package com.example.login_form.java;

import org.json.JSONArray;

public class UserProfile extends UserToken {

    private String name;
    private String roles;
    private String phone;
    private String address;
    private String internalId;
    private JSONArray credential;

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

    public String getRoles() {
        return roles;
    }
    public JSONArray getCredential() {
        return credential;
    }

}

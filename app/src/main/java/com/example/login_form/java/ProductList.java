package com.example.login_form.java;

public class ProductList {

    String id;
    String name;
    String barCode;
    String RFID;

    public ProductList(String id, String name, String barCode) {
        this.id = id;
        this.name = name;
        this.barCode = barCode;
        this.RFID = RFID;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getRFID() {
        return RFID;
    }
}

package com.example.login_form;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        setTitle("Main Navigator");

        Button startbtn = findViewById(R.id.Startbtn);
        Button savebtn = findViewById(R.id.Save);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

    startbtn.setOnClickListener(v -> startActivity(new Intent(MainPage.this, Start.class)));

    savebtn.setOnClickListener(v -> startActivity(new Intent(MainPage.this, Save.class)));

    button1.setOnClickListener(v -> startActivity(new Intent(MainPage.this, Button1.class)));

    button2.setOnClickListener(v -> startActivity(new Intent(MainPage.this, Button2.class)));
    }
}

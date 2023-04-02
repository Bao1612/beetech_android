package com.example.login_form.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_form.R;

import java.util.Objects;

public class Scan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.scanner);
        setTitle("Scanner");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



    }

}

package com.example.login_form.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.login_form.R;

import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Save extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.save);
        setTitle("Save");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



    }

}

package com.example.login_form;//tiem hieu ve package



import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



import java.util.Objects;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginbtn;
    private CheckBox checkBox;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        checkBox = findViewById(R.id.checkbox);
        token = "";

    loginbtn.setOnClickListener(v -> loginbtnCLicked());
    }

    private void loginbtnCLicked() {
        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<UserToken> call = api.getLogin(username.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(@NonNull Call<UserToken> call, @NonNull Response<UserToken> response) {
//                Log.d(TAG, "onResponse: " + response.body().getToken());
                token = response.body().getToken();
                Log.d(TAG, "onResponse: " + token);
                Intent myIntent = new Intent(MainActivity.this, Navigation.class);
                startActivity(myIntent);
            }

            @SuppressLint("RtlHardcoded")
            @Override
            public void onFailure(@NonNull Call<UserToken> call, @NonNull Throwable t) {
                Toast toast =  Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 20, 30);
                toast.show();
            }
        });

        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) {
                Log.d("???", "Should auto sign in");
            }
            else {
                Log.d("???", "Should not auto sign in");
            }
        });

    }



}
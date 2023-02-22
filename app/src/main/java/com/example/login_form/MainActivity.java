package com.example.login_form;//tiem hieu ve package



import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.example.login_form.api.API;

import java.util.Objects;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private String token;
    private Button loginbtn;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        checkBox = findViewById(R.id.checkbox);

        loginbtn.setOnClickListener(v -> loginbtnCLicked());
    }



    private void loginbtnCLicked() {
        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<UserToken> call = api.getLogin(username.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(@NonNull Call<UserToken> call, @NonNull Response<UserToken> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    token = " Bearer " + response.body().getToken();
                    Log.d(TAG, "Token: " + token);
                    //Save token



                    //Passing token to ProfileFragment
                    Intent passingToken = new Intent(MainActivity.this, ProfileFragment.class);
                    passingToken.putExtra("passingToken", token);

                    Intent myIntent = new Intent(MainActivity.this, Navigation.class);
                    startActivity(myIntent);

                } else {
                    Toast toast =  Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP | Gravity.END, 20, 30);
                    toast.show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<UserToken> call, @NonNull Throwable t) {
                Toast toast =  Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.END, 20, 30);
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
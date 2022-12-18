package com.example.login_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginbtn;

    private List<User> mListUser;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);

        mListUser = new ArrayList<>();
        getLogin();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });

//        loginbtn.setOnClickListener(view -> {
//            if(username.getText().toString().equals("baole") && password.getText().toString().equals("baole")) {
//                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                Intent navbar = new Intent(this, Navigator.class);
//                startActivity(navbar);
//            } else {
//                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
//            }
//        });



    }

    private void clickLogin() {
        String strusername = username.getText().toString().trim();
        String strpassword = password.getText().toString().trim();

        if(mListUser == null || mListUser.isEmpty()) {
            return;
        }

        boolean isHasUser = false;
        for(User user : mListUser) {
            if(strusername.equals(user.getUsername()) && strpassword.equals(user.getPassword())) {
                isHasUser = true;
                mUser = user;
                break;
            }
        }

        if(isHasUser) {
            Intent navbar = new Intent(this, Navigator.class);
            startActivity(navbar);
            Bundle bundle = new Bundle();
            bundle.putSerializable("objet_user", mUser );
            intent.putExtras(bundle);
        } else {
            Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
        }

    }

    private void getLogin() {
        API.api.getLogin("api/v1/auth/login").enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mListUser = response.body();
                Log.e("Login", mListUser.size() + "");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
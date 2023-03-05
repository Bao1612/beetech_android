package com.example.login_form;//tiem hieu ve package



import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.login_form.api.API;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private EditText username;
    private String Username, Password;
    private EditText password;
    private Button loginbtn;
    private CheckBox checkBox;
    private ProgressBar spinner;
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String SHARED_PREF_NAME = "dataLogin";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";
    private static final String API_KEY = "token";
    private static final String KEY_CHECK = "check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        checkBox = findViewById(R.id.checkbox);
        spinner = findViewById(R.id.progressBar1);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        username.setText(sharedPreferences.getString(KEY_USER, ""));
        password.setText(sharedPreferences.getString(KEY_PASSWORD, ""));
        checkBox.setChecked(sharedPreferences.getBoolean(KEY_CHECK, false));



        loginbtn.setOnClickListener(v -> loginbtnCLicked());

        loginbtnCLicked();

    }


    private void loginbtnCLicked() {
        Username = username.getText().toString().trim();
        Password = password.getText().toString().trim();

        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<UserToken> call = api.getLogin(username.getText().toString().trim(), password.getText().toString().trim());
        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(@NonNull Call<UserToken> call, @NonNull Response<UserToken> response) {



                if(response.isSuccessful()) {
                    UserToken userToken = response.body();
                    editor.putString(API_KEY, userToken.token);
                    spinner.setVisibility(View.VISIBLE);

//                    Save token
                    if(checkBox.isChecked()) {
                        editor.putString(KEY_USER, Username);
                        editor.putString(KEY_PASSWORD, Password);
                        editor.putBoolean(KEY_CHECK, true);
                        editor.commit();
                    } else {
                        editor.remove(KEY_USER);
                        editor.remove(KEY_PASSWORD);
                        editor.remove(KEY_CHECK);
                        editor.commit();
                    }

                    Intent myIntent = new Intent(MainActivity.this, Navigation.class);
                    startActivity(myIntent);

                    finish();

                } else {
                    spinner.setVisibility(View.INVISIBLE);
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
    }
}
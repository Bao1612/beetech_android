package com.example.login_form;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


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

        TextView showProfile = findViewById(R.id.profile);

        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<List<UserProfile>> call = api.getProfile("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxNDE5OWRlNS1hNWVlLTQ5NmMtYjY0Ni03NTY2NDg3MzQxYTgiLCJpYXQiOjE2NzQ4Njk5Njh9.wRB0QLSFQ6V3gjH8bHYQv8dM7Y2_bRq_cyZ1RmCi6tT-l9ruaZBxN8hxGVrMah6DAd7DGHYaQ-RsEoiYU5YxZZH-KAdcXwfcQy8w8Xdw6IkUWklgYd608Kh4AHZQs0a7E9fVYcId0mjS6kEQxIdEYk069EbzVC-7Ki9GxfuUIqwXxyhlZethFgQ9wY0N6TiV-QwdB7ON-P3UTHHcrXPeDe3pBPNkmcBofhyO_odqd1jG3w3GQyW82JPfePUKibbUBnIm26XHVuNpjrnEWjk0NmVuVvrCWoeFdVy7PZOghr0SUSYierXGDpnHMxDlc6xkMFKUdTEIDFG_QO3cxSY6Jsx_JHr8iq2JouovZQs7NFQSWBvkuqVojJRrj15errxLSuNO8V8LAVaCoQs6wetCXRXfHUF42PXDQYbC7Kr1gPnjYEQe3cwo72hAOfNAlvoh0gdBTwsdlj8LrvyNR2ULf1ejaeA0mHzST2TvD_cVxoFxdNLkqLpqtyUbYuGHVj0UrmdbpECQUerrGqk4ZDYqUIaDEPGrgYTyBMNWLA_kWKn78vfeidKmQ339Zx9CTo2DlUM2J9cjmRmBoIJTD1N8Psqi_l0SOC3UQfmGs4h3DhcJgwAX7QcPZyR3xqtkRGIxqX41AszCY-8-aAcdLPxYLFRz2Yo8CUl95jIZZa6w5S0");

        call.enqueue(new Callback<List<UserProfile>>() {
            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                if(!response.isSuccessful()) {
                    showProfile.setText("Fail!!");
                    return;
                }

                List<UserProfile> userProfiles = response.body();
                for(UserProfile userProfile : userProfiles) {
                    String content = "";
                    content += "Name: " + userProfile.getName() + "\n";
                    content += "SĐT: " + userProfile.getPhone() + "\n";
                    content += "Address: " + userProfile.getAddress() + "\n";
                    showProfile.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {
                showProfile.setText(t.getMessage());
            }
        });

    }

}

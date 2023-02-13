package com.example.login_form;

import static android.content.ContentValues.TAG;

import android.accounts.AccountManager;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Save extends AppCompatActivity {

    String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI0OTYwZjdkMC01MTdkLTQ0MzgtODA4MS1hZmRiZTI5YjU3M2UiLCJpYXQiOjE2NzYyOTQ1NDh9.UHhLqfo2Kqc8j0_Z8Z7PEtMfAgCAsNj8-tQqaV2uRsQxg1W9jUOXB4SUIt33_4A7VUpA4JU6aSv-ute7CNTBx3nDAxoJZfaQIGYsTaQ_h4B1ZmuTHXJgaJNqW06dPt2MFwD6zXCMjt-20irJ3ubvYhkuN9BS3LaFiVS4Sl_z3sA8TCXMp3QKPr4oM0MungaNM2BsEfPsgDMnSgDktYnEucSHDDZLYuqNj7rVaMxyOTwLAXI_1m4R11RXbxDmbAzDMCb8aS3IZ6Jb5wG7uvqDurqL0l95MzslX83Zo6dpFhgXvAW4aHFI4yC5xcscCibs2P2AOqzqSlEmQroMNWLr8JQ3PE81scb7SWxWlnqYLTmPlGP2haI_rP8BtLK0tE6GvO0P8j4mEPTmBG0NG1bSB8NwkawksASjtq9Ss6bgnTlTaKZItu4Xhn1Oc1F1vconh0HCSPJc_OSScN-6jMPkcKtIBvWM8P6Jj26HFEJzoUeT1S8GBJxWjLb_UalA_fqBTnd3TYAX4faIe-qt6VaGPoklqMcepRkJJzxrz2fQJ6Rmr8HwtNKKxV-Ic5Ct0FS0Ff6KbBi96xYAVkPLH40SR2Ima4otU_T0s9FMqCg51s_3Pdim6VrO7_9-rjJQBesRn7mQ5ifJ71JxbFX8CHv0eglkcfM8rs7_xfLTBb9ksUE";

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.save);
        setTitle("Save");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TextView showProfile = findViewById(R.id.showprofile);

        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<List<UserProfile>> call = api.getProfile(" Bearer " + token);
        call.enqueue(new Callback<List<UserProfile>>() {
            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                if(response.isSuccessful()) {
                    showProfile.setText("FAIL!!!");
                    return;
                }

                List<UserProfile> userProfiles = response.body();
                for(UserProfile userProfile : userProfiles) {
                    String content = "";
                    content  += "Name: " +userProfile.getName() + "\n";
                    content += "Phone: " + userProfile.getPhone() + "\n";
                    content += "Address: " + userProfile.getAddress() + "\n";
                    showProfile.append(content);
                }

            }


            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {

            }
        });


    }
}

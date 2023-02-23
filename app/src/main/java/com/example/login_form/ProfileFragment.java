package com.example.login_form;


import static android.content.ContentValues.TAG;
import static com.example.login_form.R.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_form.api.API;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(layout.fragment_profile, container, false);

        TextView showProfile = view.findViewById(id.userProfile);

        Intent intent = getActivity().getIntent();
        String token = intent.getStringExtra("passingToken");



        Log.d(TAG,"Passed token: " + token);
        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<UserProfile> callProfile = api.getProfile(token);
        callProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(@NonNull Call<UserProfile> call, @NonNull Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getActivity(),"Author token thành công",Toast.LENGTH_SHORT).show();
                    String content = "";
                    assert response.body() != null;
                    content += "Name: " + response.body().getName() + "\n";
                    content += "Phone: " + response.body().getPhone() + "\n";
                    content += "Address: " + response.body().getAddress() + "\n";
                    showProfile.append(content);
                } else {
                    Toast.makeText(getActivity(),"Author token thât bại",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserProfile> call, @NonNull Throwable t) {

            }
        });


        return view;
    }
}
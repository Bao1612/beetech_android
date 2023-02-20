package com.example.login_form;

import static com.example.login_form.R.*;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIwMjJjMGMyNC03ODQ4LTRlMTAtYTg0ZS1iMDkzYzBlMDM5NTMiLCJpYXQiOjE2NzY4NzkxNDh9.aawoXI3R2-qrgxAjAsQWK8rHPrcbhdkOHaJRKLE5ZwRJ3IF5vYYj94FLCEeHs-wZQHoEA7Q2npPuNKo7qfRZMeVeLvzJLdD0kwx2nT2YEFdPIBOirwTLhj1hR3XOGXESq-87Q5rEpFweJ_Ysjaxowe1blWFnxyXFK1qX1wCiAZekzVF3uYKJCDb2YmyWLg-qfWkHLWYTG0WiUTHqLFy2NbKxoM8afV9qq6LZ569sh8883t9P14JTcHNDd1H3iQsKb1NA_r72lMrlQiBjGcpvWVI8GWwuTkv2FvRiVvgVBY2CTkpTUHSbhRFrN7_m1iLhcX5GEXkYAMcYunNqNfoRK7gZOlp5aMBjFPBrXmX5HZgZBirsqZgDUCDP5jbq4Cq1qgrUlCyomTqSbj4TCf6z9ZS1JtUgvlr9uommWqT6Yf-B2xE8oqbPy_1PMxnJckGybrf3hqFGM3JHJG0iKzPjIh_wm0pdibFYwRYrW2PkZ2Ao5VhhJzE_mGX9Aea1MZ_cqGLUX0KBiArM01U8V2awJ8dmR-IzF_dZOe9WchcvtGMcgLJagi17CjaVu0AA9fNGY56n7pN8CzSMd-QqyVlArnjL2n0Ep-My9Ic0a8Lz5dsLfnMuDxCysr6nBgPIFy03Ym4MdbGEHGwTg5WveXA8JNGPnT3UVUEJpaR5DRHm0rE";

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(layout.fragment_profile, container, false);

        TextView showProfile = view.findViewById(id.userProfile);

        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<UserProfile> callProfile = api.getProfile(token);
        callProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getActivity(),"Author token thành công",Toast.LENGTH_SHORT).show();
                    String content = "";
                    content += "Name: " + response.body().getName() + "\n";
                    content += "Phone: " + response.body().getPhone() + "\n";
                    content += "Address: " + response.body().getAddress() + "\n";
                    showProfile.append(content);
                } else {
                    Toast.makeText(getActivity(),"Author token thât bại",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });


        return view;
    }
}
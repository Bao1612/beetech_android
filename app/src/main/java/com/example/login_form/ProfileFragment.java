package com.example.login_form;

import static com.example.login_form.R.*;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(layout.fragment_profile, container, false);


//        TextView userProfileResult = view.findViewById(id.userProfile);
//
//
//        API userProfileAPI = RetrofitClient.getRetrofitInstance().create(API.class);
//        Call<List<UserProfile>> call = userProfileAPI.getProfile("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxNDE5OWRlNS1hNWVlLTQ5NmMtYjY0Ni03NTY2NDg3MzQxYTgiLCJpYXQiOjE2NzQ4Njk5Njh9.wRB0QLSFQ6V3gjH8bHYQv8dM7Y2_bRq_cyZ1RmCi6tT-l9ruaZBxN8hxGVrMah6DAd7DGHYaQ-RsEoiYU5YxZZH-KAdcXwfcQy8w8Xdw6IkUWklgYd608Kh4AHZQs0a7E9fVYcId0mjS6kEQxIdEYk069EbzVC-7Ki9GxfuUIqwXxyhlZethFgQ9wY0N6TiV-QwdB7ON-P3UTHHcrXPeDe3pBPNkmcBofhyO_odqd1jG3w3GQyW82JPfePUKibbUBnIm26XHVuNpjrnEWjk0NmVuVvrCWoeFdVy7PZOghr0SUSYierXGDpnHMxDlc6xkMFKUdTEIDFG_QO3cxSY6Jsx_JHr8iq2JouovZQs7NFQSWBvkuqVojJRrj15errxLSuNO8V8LAVaCoQs6wetCXRXfHUF42PXDQYbC7Kr1gPnjYEQe3cwo72hAOfNAlvoh0gdBTwsdlj8LrvyNR2ULf1ejaeA0mHzST2TvD_cVxoFxdNLkqLpqtyUbYuGHVj0UrmdbpECQUerrGqk4ZDYqUIaDEPGrgYTyBMNWLA_kWKn78vfeidKmQ339Zx9CTo2DlUM2J9cjmRmBoIJTD1N8Psqi_l0SOC3UQfmGs4h3DhcJgwAX7QcPZyR3xqtkRGIxqX41AszCY-8-aAcdLPxYLFRz2Yo8CUl95jIZZa6w5S0");
//        call.enqueue(new Callback<List<UserProfile>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<UserProfile>> call, @NonNull Response<List<UserProfile>> response) {
//                if(!response.isSuccessful()) {
//                    Toast.makeText(getActivity(),"Không tìm thấy dữ liệu",Toast.LENGTH_SHORT).show();
//                }
//
//                List<UserProfile> userProfiles = response.body();
//                assert userProfiles != null;
//                for(UserProfile userProfile : userProfiles) {
//                    String content = "";
//                    content += "Username: " + userProfile.getName() + "\n";
//                    content += "SĐT: " + userProfile.getPhone() + "\n";
//                    content += "Address: " + userProfile.getAddress();
//                    userProfileResult.append(content);
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<UserProfile>> call, @NonNull Throwable t) {
//
//            }
//        });


        return view;
    }
}
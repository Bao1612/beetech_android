package com.example.login_form.profile;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.example.login_form.R.id;
import static com.example.login_form.R.layout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.login_form.R;
import com.example.login_form.api.API;
import com.example.login_form.api.RetrofitClient;
import com.example.login_form.java.UserProfile;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


    //Save user dataa
    private String fullName, internalID;
    private TextView empName, empRole;

    SharedPreferences userData;
    private SharedPreferences.Editor saveUserData;
    private static final String SHARED_PREF_USER = "datauser";
    private static final String FULL_NAME = "fullName";
    private static final String INTERNAL_ID = "internalID";

    //
    //Get user token
    private static final String SHARED_PREF_NAME = "dataLogin";
    private SharedPreferences sharedPreferences;
    public static final String API_KEY = "token";
    public static final String KEY_USER = "user";
    public static final String KEY_PASSWORD = "password";
    /////
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater lf = requireActivity().getLayoutInflater();
        View view =  lf.inflate(layout.fragment_profile, container, false);

        empName = view.findViewById(R.id.empName);
        empRole = view.findViewById(R.id.empRole);
        //Save user data
        userData = getActivity().getSharedPreferences(SHARED_PREF_USER,MODE_PRIVATE);
        saveUserData = userData.edit();
        //
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String beererToken = "Bearer " + sharedPreferences.getString(API_KEY, "");
        Log.wtf(TAG, "bearer token: " + beererToken);
        FloatingActionButton chooseImg = view.findViewById(id.chooseImg);

        //Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor));
        chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileFragment.this)
                        .crop()	    //Crop image and let user choose aspect ratio.
                        .start();
            }
        });

        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<UserProfile> callprofile = api.getProfile(beererToken);
        callprofile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    Response<UserProfile> json = response;
                    empName.setText(response.body().getName());
                    JSONArray jsonArray = response.body().getCredential();
                    Log.d("DEBUG_PROFILE", "onResponse" + jsonArray);

                    for(int i = 0; i < jsonArray.length(); i++) {

                    }

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });


        return view;
    }
}
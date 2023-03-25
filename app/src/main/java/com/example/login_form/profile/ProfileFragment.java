package com.example.login_form.profile;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.example.login_form.R.*;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login_form.R;
import com.example.login_form.api.API;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {


    //Save user dataa
    private String fullName, internalID;

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

        return view;
    }
}
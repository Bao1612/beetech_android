package com.example.login_form.profile;

import static android.content.Context.MODE_PRIVATE;
import static com.example.login_form.R.id;
import static com.example.login_form.R.layout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.login_form.R;
import com.example.login_form.api.API;
import com.example.login_form.api.RetrofitClient;
import com.example.login_form.java.UserProfile;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private TextView dateCheckin, timeCheckin, dateCheckout, timeCheckout;
    private Button checkinBtn, checkoutBtn;
    //Save user dataa
    private String fullName, internalID;
    private TextView empName, empID;

    SharedPreferences userData;
    private static final String SHARED_PREF_USER = "datauser";
    private static final String FULL_NAME = "fullName";
    private static final String INTERNAL_ID = "internalID";

    //
    //Get user token
    private static final String SHARED_PREF_NAME = "dataLogin";
    private SharedPreferences sharedPreferences;
    public static final String API_KEY = "token";
    /////
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutInflater lf = requireActivity().getLayoutInflater();
        View view =  lf.inflate(layout.fragment_profile, container, false);

        dateCheckout = view.findViewById(R.id.dateCheckout);
        timeCheckout = view.findViewById(R.id.timeCheckout);
        dateCheckin = view.findViewById(R.id.dateCheckin);
        timeCheckin = view.findViewById(R.id.timeCheckin);
        empName = view.findViewById(R.id.empName);
        empID = view.findViewById(R.id.empID);
        checkinBtn = view.findViewById(R.id.checkinBtn);
        checkoutBtn = view.findViewById(R.id.checkoutBtn);
        //Save user data
        userData = getActivity().getSharedPreferences(SHARED_PREF_USER,MODE_PRIVATE);
        SharedPreferences.Editor saveUserData = userData.edit();
        //
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String beererToken = "Bearer " + sharedPreferences.getString(API_KEY, "");
        FloatingActionButton chooseImg = view.findViewById(id.chooseImg);

        //Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor));
        chooseImg.setOnClickListener(v -> ImagePicker.with(ProfileFragment.this)
                .crop()	    //Crop image and let user choose aspect ratio.
                .start());

        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<UserProfile> callprofile = api.getProfile(beererToken);
        callprofile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(@NonNull Call<UserProfile> call, @NonNull Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    empName.setText(response.body().getName());
                    empID.setText("Emp ID: " + response.body().getInternalID());
                    saveUserData.putString(FULL_NAME, response.body().getName());
                    saveUserData.putString(INTERNAL_ID, response.body().getInternalID());
                    saveUserData.commit();

                }
            }

            @Override
            public void onFailure(@NonNull Call<UserProfile> call, @NonNull Throwable t) {

            }
        });

        checkinBtn.setOnClickListener(v -> {
            checkinBtn.setVisibility(View.GONE);
            checkoutBtn.setVisibility(View.VISIBLE);

            SimpleDateFormat realDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String currentDate = realDate.format(new Date());
            dateCheckin.setText("Date: " + currentDate);


            SimpleDateFormat realTime = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String currentTime = realTime.format(new Date());
            timeCheckin.setText("Time: " + currentTime);

        });

        checkoutBtn.setOnClickListener(v -> {

            SimpleDateFormat realDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            String currentDate = realDate.format(new Date());
            dateCheckout.setText("Date: " + currentDate);


            SimpleDateFormat realTime = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String currentTime = realTime.format(new Date());
            timeCheckout.setText("Time: " + currentTime);
            checkoutBtn.setEnabled(false);
        });


        return view;
    }
}
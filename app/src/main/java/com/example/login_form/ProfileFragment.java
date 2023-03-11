package com.example.login_form;

import static android.content.Context.MODE_PRIVATE;
import static com.example.login_form.R.*;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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


    //Save user data
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

        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(layout.fragment_profile, container, false);
        //Save user data
        userData = getActivity().getSharedPreferences(SHARED_PREF_USER,MODE_PRIVATE);
        saveUserData = userData.edit();
        //

        TextView showProfile = view.findViewById(id.userProfile);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String beererToken = "Bearer " + sharedPreferences.getString(API_KEY, "");

        API api = RetrofitClient.getRetrofitInstance().create(API.class);
        Call<UserProfile> callProfile = api.getProfile(beererToken);
        callProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(@NonNull Call<UserProfile> call, @NonNull Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    String content = "";
                    fullName = response.body().getName();
                    internalID = response.body().getInternalID();
                    assert response.body() != null;
                    content += "Name: " + fullName + "\n";
                    content += "Phone: " + response.body().getPhone() + "\n";
                    content += "Address: " + response.body().getAddress() + "\n";
                    content += "InternalID: " + internalID;
                    showProfile.append(content);

                    saveUserData.putString(FULL_NAME, fullName);
                    saveUserData.putString(INTERNAL_ID, internalID);
                    saveUserData.commit();

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
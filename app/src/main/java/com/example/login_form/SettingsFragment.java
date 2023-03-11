package com.example.login_form;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SettingsFragment extends Fragment {

    private static final String SHARED_PREF_NAME = "dataLogin";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor logoutEditor;

    public static final String API_KEY = "token";
    public static final String KEY_USER = "user";
    public static final String KEY_PASSWORD = "password";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        // Inflate the layout for this fragment
        Button btnLogout = view.findViewById(R.id.logout_btn);
        btnLogout.setBackgroundColor(Color.RED);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        logoutEditor = sharedPreferences.edit();
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(myIntent);
                sharedPreferences.getString(API_KEY, "");
                sharedPreferences.getString(KEY_USER, "");
                sharedPreferences.getString(KEY_PASSWORD, "");
                logoutEditor.remove(KEY_PASSWORD);
                logoutEditor.remove(KEY_USER);
                logoutEditor.remove(API_KEY);
                logoutEditor.commit();
            }
        });

        return view;
    }
}
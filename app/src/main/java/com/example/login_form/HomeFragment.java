package com.example.login_form;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        Button startbtn = getView().findViewById(R.id.Startbtn);
//        Button savebtn = getView().findViewById(R.id.Save);
//        Button button1 = getView().findViewById(R.id.button1);
//        Button button2 = getView().findViewById(R.id.button2);
//
//
//        startbtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), Start.class)));
//
//        savebtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), Save.class)));
//
//        button1.setOnClickListener(v -> startActivity(new Intent(getActivity(), Button1.class)));
//
//        button2.setOnClickListener(v -> startActivity(new Intent(getActivity(), Button2.class)));
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button startbtn = view.findViewById(R.id.Startbtn);
        Button savebtn =  view.findViewById(R.id.Save);
        Button button1 =  view.findViewById(R.id.button1);
        Button button2 =  view.findViewById(R.id.button2);


        startbtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), Start.class)));

        savebtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), Save.class)));

        button1.setOnClickListener(v -> startActivity(new Intent(getActivity(), Button1.class)));

        button2.setOnClickListener(v -> startActivity(new Intent(getActivity(), Button2.class)));
        return view;
    }
}
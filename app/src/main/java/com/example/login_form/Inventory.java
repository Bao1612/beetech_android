package com.example.login_form;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;


public class Inventory extends AppCompatActivity {

    StepView stepView;
    Button btnNext, btnBack;
    TextView showEmpID, showEmpName;

    Spinner spinner;
    String[] county = {"Viet Nam", "USA", "JAPAN", "LAO"};

    //Get user data form database
    private static final String SHARED_PREF_USER = "datauser";
    private SharedPreferences getUserData;
    public static final String FULL_NAME = "fullName";
    public static final String INTERNAL_ID = "internalID";

    int stepIndex = 0;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_inventory);
        setTitle("Inventory");


        stepView = findViewById(R.id.step_view);
        btnNext = findViewById(R.id.next);
        btnBack = findViewById(R.id.back);
        showEmpID = findViewById(R.id.showEmpID);
        showEmpName = findViewById(R.id.showEmpName);
        spinner = findViewById(R.id.spinner);

        //Set user data
        getUserData = getSharedPreferences(SHARED_PREF_USER, MODE_PRIVATE);
        showEmpID.setText(getUserData.getString(FULL_NAME, ""));
        showEmpName.setText(getUserData.getString(INTERNAL_ID, ""));
        //

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Inventory.this, android.R.layout.simple_spinner_item, county);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(Inventory.this, county[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext();
            }


        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }

    private void goNext() {
        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
        stepView.done(true);
        nextStep();
    }

    private void goBack() {
        stepView.getState()
                .animationType(StepView.ANIMATION_LINE)
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
        backStep();
    }

    public void nextStep() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stepIndex++;
                if(stepIndex == 2) {
                    btnNext.setVisibility(View.INVISIBLE);
                    stepView.go(stepIndex, true);
                } else if(stepIndex < 2) {
                    stepView.go(stepIndex, true);
                    btnBack.setVisibility(View.VISIBLE);
                }
            }
        }, 0);
    }

    public void backStep() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stepIndex--;
                if(stepIndex == 0) {
                    btnBack.setVisibility(View.INVISIBLE);
                    stepView.go(stepIndex, true);
                    stepView.done(false);
                } else if(stepIndex >= 0) {
                    stepView.go(stepIndex, true);
                    btnNext.setVisibility(View.VISIBLE);
                }


            }
        }, 0);
    }
}


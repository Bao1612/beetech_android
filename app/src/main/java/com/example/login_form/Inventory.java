package com.example.login_form;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.shuhart.stepview.StepView;

import java.util.ArrayList;


public class Inventory extends AppCompatActivity {

    StepView stepView;
    TextView stepTextView;
    TextView descriptionTextView;
    Button btnNext, btnBack;

    int stepIndex = 0;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_inventory);
        setTitle("Inventory");


        stepView = findViewById(R.id.step_view);
        btnNext = findViewById(R.id.next);
        btnBack = findViewById(R.id.back);

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


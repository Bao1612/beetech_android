package com.example.login_form;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.shuhart.stepview.StepView;


public class Inventory extends AppCompatActivity {

    StepView stepView;
    TextView stepTextView;
    TextView descriptionTextView;
    Button btnNext, btnBack;

    int stepIndex = 0;
    String[] stepText = {"Page 1", "Page 2", "Page 3", "Page 4"};
    String[] descriptionText = {"Lorem page 1", "Lorem page 2", "Lorem page 3", "Lorem page 4"};

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_inventory);
        setTitle("Inventory");

        stepTextView = findViewById(R.id.stepTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
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
                stepView.getState()
                        .animationType(StepView.ANIMATION_ALL)
                        .stepsNumber(4)
                        .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                        .commit();
                goBack();
            }
        });

    }

    private void goNext() {
        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(4)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();
        nextStep();
    }

    private void goBack() {
        View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        // Note that system bars will only be "visible" if none of the
                        // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                        if (stepIndex == 0) {
                            btnBack.setVisibility(View.INVISIBLE);
                        } else if(stepIndex <= 3 && stepIndex > 0){
                            btnBack.setVisibility(View.VISIBLE);
                        }
                    }
                });
        backStep();
    }

    public void nextStep() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stepIndex++;
                if(stepIndex <= stepText.length) {
                    stepTextView.setText(stepText[stepIndex]);
                    descriptionTextView.setText(descriptionText[stepIndex]);
                    stepView.go(stepIndex, true);
                    Log.d(TAG, "stepIndex" + stepIndex);
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
                if(stepIndex <= stepText.length) {
                    stepTextView.setText(stepText[stepIndex]);
                    descriptionTextView.setText(descriptionText[stepIndex]);
                    stepView.go(stepIndex, true);
                    Log.d(TAG, "stepIndexBack" + stepIndex);
                }


            }
        }, 0);
    }
}


package com.example.login_form;



import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login_form.api.API;
import com.shuhart.stepview.StepView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Inventory extends AppCompatActivity {

    StepView stepView;
    Button btnNext, btnBack;
    TextView showEmpID, showEmpName, showRealTimeDate, showRealTime;

    //Get user token
    private static final String SHARED_PREF_NAME = "dataLogin";
    private SharedPreferences sharedPreferences;
    public static final String API_KEY = "token";

    Spinner spinner;
    ArrayList<String> county = new ArrayList<String>();

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
        showRealTimeDate = findViewById(R.id.showRealTimeDate);
        showRealTime = findViewById(R.id.showRealTime);

        //Set user data
        getUserData = getSharedPreferences(SHARED_PREF_USER, MODE_PRIVATE);
        showEmpID.setText(getUserData.getString(INTERNAL_ID, ""));
        showEmpName.setText(getUserData.getString(FULL_NAME, ""));
        //
        //Get usertoken
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String token = sharedPreferences.getString(API_KEY, "");
        //
        SimpleDateFormat realDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = realDate.format(new Date());
        showRealTimeDate.setText(currentDate);

        //Call api cardview 1


        Runnable r = new Runnable() {
            public void run() {
                //Call api cardview 1
                API api = RetrofitClient.getRetrofitInstance().create(API.class);
                Call<Stores> callStore = api.getStore(token);
                callStore.enqueue(new Callback<Stores>() {
                    @Override
                    public void onResponse(Call<Stores> call, Response<Stores> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "stores name is " + response.body().getName());
                            Toast toast = Toast.makeText(getApplicationContext(), "Author thành công", Toast.LENGTH_LONG);
                            toast.show();
                            county.add(response.body().getName());
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Author fail", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Stores> call, Throwable t) {

                    }
                });
            }
        };

        new Thread(r).start();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Inventory.this, android.R.layout.simple_spinner_item, county);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Inventory.this, county[position], Toast.LENGTH_SHORT).show();
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
        stepView.done(false);
        backStep();
    }

    public void nextStep() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stepIndex++;
                if(stepIndex == 2) {
                    findViewById(R.id.cardview3).setVisibility(View.VISIBLE);
                    findViewById(R.id.empTotal).setVisibility(View.VISIBLE);
                    findViewById(R.id.cardview2).setVisibility(View.GONE);
                    findViewById(R.id.cardview1).setVisibility(View.GONE);
                    btnNext.setVisibility(View.INVISIBLE);
                    stepView.go(stepIndex, true);
                } else if(stepIndex == 1) {
                    findViewById(R.id.cardview3).setVisibility(View.GONE);
                    findViewById(R.id.empTotal).setVisibility(View.GONE);
                    findViewById(R.id.cardview2).setVisibility(View.VISIBLE);
                    findViewById(R.id.cardview1).setVisibility(View.GONE);
                    stepView.go(stepIndex, true);
                    btnBack.setVisibility(View.VISIBLE);
                }else if(stepIndex == 0) {
                    findViewById(R.id.cardview3).setVisibility(View.GONE);
                    findViewById(R.id.empTotal).setVisibility(View.GONE);
                    findViewById(R.id.cardview2).setVisibility(View.GONE);
                    findViewById(R.id.cardview1).setVisibility(View.VISIBLE);
                    stepView.go(stepIndex, true);
                    btnBack.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
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
                if(stepIndex == 2) {
                    findViewById(R.id.cardview3).setVisibility(View.VISIBLE);
                    findViewById(R.id.empTotal).setVisibility(View.VISIBLE);
                    findViewById(R.id.cardview2).setVisibility(View.GONE);
                    findViewById(R.id.cardview1).setVisibility(View.GONE);
                    btnNext.setVisibility(View.INVISIBLE);
                    stepView.go(stepIndex, true);
                } else if(stepIndex == 1) {
                    findViewById(R.id.cardview3).setVisibility(View.GONE);
                    findViewById(R.id.empTotal).setVisibility(View.GONE);
                    findViewById(R.id.cardview2).setVisibility(View.VISIBLE);
                    findViewById(R.id.cardview1).setVisibility(View.GONE);
                    stepView.go(stepIndex, true);
                    btnBack.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                }else if(stepIndex == 0) {
                    findViewById(R.id.cardview3).setVisibility(View.GONE);
                    findViewById(R.id.empTotal).setVisibility(View.GONE);
                    findViewById(R.id.cardview2).setVisibility(View.GONE);
                    findViewById(R.id.cardview1).setVisibility(View.VISIBLE);
                    stepView.go(stepIndex, true);
                    btnBack.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                }


            }
        }, 0);
    }
}


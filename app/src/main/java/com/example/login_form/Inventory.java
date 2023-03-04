package com.example.login_form;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_form.databinding.ActivityInventoryBinding;


public class Inventory extends AppCompatActivity {

    ActivityInventoryBinding binding;
    String[] descriptionData = {"Personal", "Family", "Photo", "Final"};
    int current_state = 0;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        binding = ActivityInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Inventory");

        binding.spb.setLabels(descriptionData)
                .setBarColorIndicator(Color.BLACK)
                .setProgressColorIndicator(getResources().getColor(R.color.purple))
                .setLabelColorIndicator(getResources().getColor(R.color.purple_200))
                .setCompletedPosition(0)
                .drawView();

        binding.spb.setCompletedPosition(current_state);


        binding.btnUp.setOnClickListener(v -> {
            if(current_state < (descriptionData.length - 1)) {
                current_state=current_state + 1;
                binding.spb.setCompletedPosition(current_state).drawView();
            }
            Log.d("current_state = ", current_state + "");
        });

        binding.btnDown.setOnClickListener(v -> {
            if(current_state > 0) {
                current_state=current_state - 1;
                binding.spb.setCompletedPosition(current_state).drawView();
            }
            Log.d("current_state = ", current_state + "");
        });


    }

}

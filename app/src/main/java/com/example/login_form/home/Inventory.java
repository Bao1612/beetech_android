package com.example.login_form.home;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login_form.R;
import com.example.login_form.api.API;
import com.example.login_form.api.RetrofitClient;
import com.example.login_form.java.ProductAdapter;
import com.example.login_form.java.ProductList;
import com.example.login_form.java.Stores;
import com.shuhart.stepview.StepView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Inventory extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    StepView stepView;
    Button btnNext, btnBack;
    TextView showEmpID, showEmpName, showRealTimeDate, showRealTime;

    //Get user token
    private static final String SHARED_PREF_NAME = "dataLogin";
    public static final String API_KEY = "token";

    Spinner spinner, inventorytype, spinnerIventory, spinnerCate;

    ArrayList<String> stores_selector = new ArrayList<>();
    String[] inventory_type = {"New", "Already Exist"};
    String[] inventory = {"All", "Category"};
    String[] categories = {"Category*"};

    //Get user data form database
    private static final String SHARED_PREF_USER = "datauser";
    public static final String FULL_NAME = "fullName";
    public static final String INTERNAL_ID = "internalID";

    int stepIndex = 0;

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String token = sharedPreferences.getString(API_KEY, "");
        API api = RetrofitClient.getRetrofitInstance().create(API.class);

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_inventory);
        setTitle("Inventory");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        stepView = findViewById(R.id.step_view);
        btnNext = findViewById(R.id.next);
        btnBack = findViewById(R.id.back);
        showEmpID = findViewById(R.id.showEmpID);
        showEmpName = findViewById(R.id.showEmpName);
        spinner = findViewById(R.id.spinner);
        spinnerCate = findViewById(R.id.categories);
        inventorytype = findViewById(R.id.inventory_type);
        spinnerIventory = findViewById(R.id.inventory);

        showRealTimeDate = findViewById(R.id.showRealTimeDate);
        showRealTime = findViewById(R.id.showRealTime);

        //Set user data
        SharedPreferences getUserData = getSharedPreferences(SHARED_PREF_USER, MODE_PRIVATE);
        showEmpID.setText(getUserData.getString(INTERNAL_ID, ""));
        showEmpName.setText(getUserData.getString(FULL_NAME, ""));
        //
        //Get usertoken

        //
        SimpleDateFormat realDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = realDate.format(new Date());
        showRealTimeDate.setText(currentDate);

        //Inventory Type
        ArrayAdapter<String> adapterInventoryType = new ArrayAdapter<>(Inventory.this, android.R.layout.simple_spinner_dropdown_item, inventory_type);
        adapterInventoryType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inventorytype.setAdapter(adapterInventoryType);
        inventorytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Inventory.this, inventory_type[position], Toast.LENGTH_SHORT).show();
                if(inventory_type[position].equals("Already Exist")) {
                    findViewById(R.id.inventory).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.inventory).setVisibility(View.GONE);
                    findViewById(R.id.categories).setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Inventory
        ArrayAdapter<String> adapterInventory = new ArrayAdapter<>(Inventory.this, android.R.layout.simple_spinner_item, inventory);
        adapterInventory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIventory.setAdapter(adapterInventory);

        spinnerIventory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(inventory[position].equals("Category")) {
                    findViewById(R.id.categories).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.categories).setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Category
        ArrayAdapter<String> adapterCate = new ArrayAdapter<>(Inventory.this, android.R.layout.simple_spinner_item, categories);
        adapterCate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCate.setAdapter(adapterCate);

        spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(categories[position].equals("Category*")) {
                    Toast.makeText(Inventory.this, categories[position], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Call api cardview 1

        Call<List<Stores>> callStores = api.getStore(token);
        callStores.enqueue(new Callback<List<Stores>>() {
            @Override
            public void onResponse(@NonNull Call<List<Stores>> call, @NonNull Response<List<Stores>> response) {
                if(response.isSuccessful()) {
                    stores_selector.add("- - - SELECT STORE - - -");
                    List<Stores> stores = response.body();
                    assert stores != null;
                    for (Stores getStore : stores) {
                        stores_selector.add(getStore.getNameStore());
                    }

//                        Log.d("CATE", "select CATe: " + inventory_type);
                    //Stores
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Inventory.this, android.R.layout.simple_spinner_item, stores_selector);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(Inventory.this, setStore[position], Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }


            }

            @Override
            public void onFailure(@NonNull Call<List<Stores>> call, @NonNull Throwable t) {

            }
        });



        //Call api category



        btnNext.setOnClickListener(v -> goNext());

        btnBack.setOnClickListener(v -> goBack());

        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(this, getList());
        recyclerView.setAdapter(productAdapter);
    }

    private List<ProductList> getList() {
        List<ProductList> productList = new ArrayList<>();
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));
        productList.add(new ProductList("1", "iphone20 pro max", "1234567", "1234567"));

        return productList;
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
        handler.postDelayed(() -> {
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
        }, 0);
    }

    public void backStep() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
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
        }, 0);
    }
}
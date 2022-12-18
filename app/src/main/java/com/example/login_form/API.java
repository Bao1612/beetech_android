package com.example.login_form;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    // LINK API: http://125.212.249.230:3001/api/v1/auth/login
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd:mm:ss")
            .create();
    API api = new Retrofit.Builder()
            .baseUrl("http://125.212.249.230:3001/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(API.class);

    @GET("username")
    Call<List<User>> getLogin (@Query("api/v1") String key);

}

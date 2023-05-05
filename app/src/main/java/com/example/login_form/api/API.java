package com.example.login_form.api;

import com.example.login_form.home.Categories;
import com.example.login_form.java.Product;
import com.example.login_form.java.ProductList;
import com.example.login_form.java.Stores;
import com.example.login_form.java.UserProfile;
import com.example.login_form.java.UserToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface API {

    @FormUrlEncoded
    @POST("v1/auth/login")
    Call<UserToken> getLogin(@Field("username") String username, @Field("password") String password);

    @GET("v1/auth/profile")
    Call<UserProfile> getProfile(@Header("Authorization") String token);

    @GET("v1/stores")
    Call<List<Stores>> getStore(@Header("Authorization") String token);

    @GET("v1/categories")
    Call<List<Categories>> getCategories(@Header("Authorization") String token);

    @GET("v1/products")
    Call<Product> accessProducts (@Header("Authorization") String token, @Query("iCategoryId") String iCategoryId);

}

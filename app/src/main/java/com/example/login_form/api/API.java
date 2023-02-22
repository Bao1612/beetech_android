package com.example.login_form.api;

import com.example.login_form.UserProfile;
import com.example.login_form.UserToken;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("auth/login")
    Call<UserToken> getLogin(@Field("username") String username, @Field("password") String password);

    @GET("auth/profile")
    Call<UserProfile> getProfile(@Header("Authorization") String token);

}

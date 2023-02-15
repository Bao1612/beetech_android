package com.example.login_form;



import java.util.List;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.POST;


public interface API {

    @FormUrlEncoded
    @POST("login")
    Call<UserToken> getLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @GET("profile")
    Call<List<UserProfile>> getProfile(@Field("token") String token);

}

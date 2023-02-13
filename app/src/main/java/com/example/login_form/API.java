package com.example.login_form;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.Header;

import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {

    @FormUrlEncoded
    @POST("login")
    Call<UserToken> getLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded

    @POST("profile")
    Call<List<UserProfile>> getProfile (@Header("Authorization") String token);

}

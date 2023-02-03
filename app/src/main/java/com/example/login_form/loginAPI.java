package com.example.login_form;



import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.POST;


public interface loginAPI {

    @FormUrlEncoded
    @POST("login")
    Call<UserToken> getLogin(@Field("username") String username, @Field("password") String password);

}

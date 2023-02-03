package com.example.login_form;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolder {

    @GET("posts")
    Call<List<Post>> getPosts();

}

package com.example.login_form.api;

import com.example.login_form.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface JsonPlaceHolder {

    @GET("posts")
    Call<List<Post>> getPosts();

}

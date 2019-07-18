package com.example.template_dpr_now.api;

import com.example.template_dpr_now.YouTubeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET
    Call<YouTubeModel> getVideos(@Url String url);
}
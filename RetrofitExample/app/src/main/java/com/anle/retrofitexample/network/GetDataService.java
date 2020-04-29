package com.anle.retrofitexample.network;

import com.anle.retrofitexample.model.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// Interface defines HTTP operations

public interface GetDataService {
    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}

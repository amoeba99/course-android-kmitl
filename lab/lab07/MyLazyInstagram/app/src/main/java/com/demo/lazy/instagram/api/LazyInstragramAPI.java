package com.demo.lazy.instagram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Amoeba on 10/6/2017.
 */

public interface LazyInstragramAPI {
    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";
    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);
}

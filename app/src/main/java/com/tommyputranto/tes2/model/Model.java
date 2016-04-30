package com.tommyputranto.tes2.model;

import com.tommyputranto.tes2.API.Api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by tommy on 4/27/16.
 */
public interface Model {
    @GET("/list/{id}")
    public void getFeed(@Path("id") String id, Callback<Api> response);
}

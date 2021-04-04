package com.softexpert.test.data.api;


import com.softexpert.test.data.models.CarsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI {

    @GET("cars")
    Single<CarsResponse> getCarsList(@Query("page") int page);
}

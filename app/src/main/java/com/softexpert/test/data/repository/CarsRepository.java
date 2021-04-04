package com.softexpert.test.data.repository;


import com.softexpert.test.data.api.ApiClient;
import com.softexpert.test.data.api.ServiceAPI;
import com.softexpert.test.data.models.CarsResponse;

import io.reactivex.Single;

public class CarsRepository {

    private ServiceAPI serviceAPI;

    public CarsRepository() {
        serviceAPI = ApiClient.getClient().create(ServiceAPI.class);
    }

    public Single<CarsResponse> getCarsList(int page) {
        return serviceAPI.getCarsList(page);
    }
}

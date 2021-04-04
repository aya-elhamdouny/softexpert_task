
package com.softexpert.test.data.models;

import java.util.List;
import com.google.gson.annotations.Expose;


public class CarsResponse {

    @Expose
    private List<CarsResponseData> data;
    @Expose
    private Long status;

    public List<CarsResponseData> getData() {
        return data;
    }

    public Long getStatus() {
        return status;
    }

    public static class Builder {

        private List<CarsResponseData> data;
        private Long status;

        public CarsResponse.Builder withData(List<CarsResponseData> data) {
            this.data = data;
            return this;
        }

        public CarsResponse.Builder withStatus(Long status) {
            this.status = status;
            return this;
        }

        public CarsResponse build() {
            CarsResponse carsResponse = new CarsResponse();
            carsResponse.data = data;
            carsResponse.status = status;
            return carsResponse;
        }

    }

}

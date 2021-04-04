package com.softexpert.test.data.models;

import com.google.gson.annotations.Expose;


public class CarsResponseData {

    @Expose
    private String brand;
    @Expose
    private String constractionYear;
    @Expose
    private Long id;
    @Expose
    private String imageUrl;
    @Expose
    private Boolean isUsed;

    public String getBrand() {
        return brand;
    }

    public String getConstractionYear() {
        return constractionYear;
    }

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public static class Builder {

        private String brand;
        private String constractionYear;
        private Long id;
        private String imageUrl;
        private Boolean isUsed;

        public CarsResponseData.Builder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarsResponseData.Builder withConstractionYear(String constractionYear) {
            this.constractionYear = constractionYear;
            return this;
        }

        public CarsResponseData.Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public CarsResponseData.Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public CarsResponseData.Builder withIsUsed(Boolean isUsed) {
            this.isUsed = isUsed;
            return this;
        }

        public CarsResponseData build() {
            CarsResponseData carsResponseData = new CarsResponseData();
            carsResponseData.brand = brand;
            carsResponseData.constractionYear = constractionYear;
            carsResponseData.id = id;
            carsResponseData.imageUrl = imageUrl;
            carsResponseData.isUsed = isUsed;
            return carsResponseData;
        }

    }

}

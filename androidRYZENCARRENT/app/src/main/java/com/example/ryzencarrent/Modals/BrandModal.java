package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class BrandModal {

    @SerializedName("brand_name")
    private String brand_name;

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }
}

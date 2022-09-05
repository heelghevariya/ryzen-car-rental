package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class DriverModal {

    @SerializedName("d_id")
    private int d_id;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("driver_fees")
    private int driver_fees;
    @SerializedName("experience")
    private int experience;

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getDriver_fees() {
        return driver_fees;
    }

    public void setDriver_fees(int driver_fees) {
        this.driver_fees = driver_fees;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}

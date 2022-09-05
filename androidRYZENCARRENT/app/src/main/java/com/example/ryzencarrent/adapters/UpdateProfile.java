package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class UpdateProfile {


    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("country")
    private String country;
    @SerializedName("address")
    private String address;
    @SerializedName("dob")
    private String dob;
    @SerializedName("mobile_no")
    private String mobile_no;

    @SerializedName("uid")
    private int uid;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}

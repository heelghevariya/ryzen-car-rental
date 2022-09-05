package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class TotalDays {


    @SerializedName("fdate")
    private String fdate;

    @SerializedName("tdate")
    private int tdate;

    @SerializedName("tday")
    private int tday;

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public int getTdate() {
        return tdate;
    }

    public void setTdate(int tdate) {
        this.tdate = tdate;
    }

    public int getTday() {
        return tday;
    }

    public void setTday(int tday) {
        this.tday = tday;
    }
}

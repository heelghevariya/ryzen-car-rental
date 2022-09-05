package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class PayUPIModal {


    @SerializedName("bno")
    private int bno;

    @SerializedName("apaid")
    private int apaid;
    @SerializedName("status")
    private int status;

    public int getApaid() {
        return apaid;
    }

    public void setApaid(int apaid) {
        this.apaid = apaid;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
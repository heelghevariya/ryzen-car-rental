package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class PayAtBranch {


    @SerializedName("bno")
    private int bno;
    @SerializedName("status")
    private int status;

    @SerializedName("apaid")
    private int apaid;

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
package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class Getcarrating {


    @SerializedName("vid")
    private int vid;

    @SerializedName("status")
    private float status;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public float getStatus() {
        return status;
    }

    public void setStatus(float status) {
        this.status = status;
    }
}



package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class RatingModal {


    @SerializedName("uid")
    private int uid;

    @SerializedName("status")
    private int status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

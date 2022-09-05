package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class GetUserCarRating {


    @SerializedName("bno")
    private int bno;

    @SerializedName("vrating")
    private int vrating;

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public int getVrating() {
        return vrating;
    }

    public void setVrating(int vrating) {
        this.vrating = vrating;
    }
}



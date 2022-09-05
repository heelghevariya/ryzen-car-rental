package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class Addcarrating {


    @SerializedName("bno")
    private int bno;

    @SerializedName("rating")
    private int rating;

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}



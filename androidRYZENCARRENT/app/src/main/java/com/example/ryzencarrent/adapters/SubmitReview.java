package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class SubmitReview {
    @SerializedName("uid")
    private int uid;

    @SerializedName("review")
    private String review;

    @SerializedName("rating")
    private int rating;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

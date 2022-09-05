package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class ReviewModal {


    @SerializedName("user_name")
    private String user_name;

    @SerializedName("rating")
    private int rating;

    @SerializedName("review")
    private String review;

    public ReviewModal(String user_name, String review, int rating) {
        this.user_name = user_name;
        this.review = review;
        this.rating = rating;

    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}

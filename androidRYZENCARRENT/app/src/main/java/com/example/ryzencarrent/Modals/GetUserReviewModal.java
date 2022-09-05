package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class GetUserReviewModal {

    @SerializedName("uid")
    private int uid;

    @SerializedName("rating")
    private int rating;
    @SerializedName("review")
    private String review;
    @SerializedName("status")
    private int status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

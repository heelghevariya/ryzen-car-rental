package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class InquiryModal {

    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("mno")
    private String mno;
    @SerializedName("message")
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

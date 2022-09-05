package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginData {


    @SerializedName("username")
    private String username;

    @SerializedName("userid")
    private int userid;

    @SerializedName("usermail")
    private String usermail;

    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

}

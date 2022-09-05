package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class usermailchangedata {
    @SerializedName("uid")
    private int uid;

    @SerializedName("mail")
    private String mail;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

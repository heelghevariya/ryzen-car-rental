package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class UpdatePassword {


    @SerializedName("uid")
    private int uid;
@SerializedName("status")
    private int status;
@SerializedName("currentpass")
    private int currentpass;
@SerializedName("newpass")
    private int newpass;

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

    public int getCurrentpass() {
        return currentpass;
    }

    public void setCurrentpass(int currentpass) {
        this.currentpass = currentpass;
    }

    public int getNewpass() {
        return newpass;
    }

    public void setNewpass(int newpass) {
        this.newpass = newpass;
    }
}

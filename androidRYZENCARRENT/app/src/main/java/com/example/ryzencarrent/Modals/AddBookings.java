package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class AddBookings {


    @SerializedName("fdate")
    private String fdate;

    @SerializedName("tdate")
    private String tdate;

    @SerializedName("msg")
    private String msg;

    @SerializedName("uid")
    private int uid;

    @SerializedName("vid")
    private int vid;

    @SerializedName("did")
    private int did;
    @SerializedName("status")
    private int status;

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

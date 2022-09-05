package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class Subscribe {


    @SerializedName("umail")
    private int umail;
    @SerializedName("submail")
    private int submail;
    @SerializedName("status")
    private int status;

    public int getUmail() {
        return umail;
    }

    public void setUmail(int umail) {
        this.umail = umail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

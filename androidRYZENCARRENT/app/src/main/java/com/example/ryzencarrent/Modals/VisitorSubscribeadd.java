package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class VisitorSubscribeadd {


    @SerializedName("umail")
    private String umail;
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }
}

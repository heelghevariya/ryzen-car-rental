package com.example.ryzencarrent.adapters;

import com.google.gson.annotations.SerializedName;

public class GetPageDataModal {


    @SerializedName("ptype")
    private String ptype;
    @SerializedName("detail")
    private String detail;

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

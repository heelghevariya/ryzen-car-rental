package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class CompanyModal {



    @SerializedName("address")
    private String address;

    @SerializedName("email_id")
    private String email_id;

    @SerializedName("contact_no")
    private String contact_no;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
}

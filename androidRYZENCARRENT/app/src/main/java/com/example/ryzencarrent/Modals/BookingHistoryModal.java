package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class BookingHistoryModal {

    @SerializedName("uid")
    private int uid;

    @SerializedName("booking_no")
    private int booking_no;

    @SerializedName("mobile_no")
    private String mobile_no;
    @SerializedName("address")
    private String address;
    @SerializedName("state")
    private String state;
    @SerializedName("country")
    private String country;
    @SerializedName("cadd")
    private String cadd;
    @SerializedName("cno")
    private String cno;
    @SerializedName("cmail")
    private String cmail;
    @SerializedName("booking_date")
    private String booking_date;
    @SerializedName("b_status")
    private int b_status;
    @SerializedName("from_date")
    private String from_date;
    @SerializedName("to_date")
    private String to_date;
    @SerializedName("totalnodays")
    private int totalnodays;
    @SerializedName("p_status")
    private int p_status;
    @SerializedName("amount_paid")
    private int amount_paid;
    @SerializedName("brand_name")
    private String brand_name;
    @SerializedName("vehicle_title")
    private String vehicle_title;
    @SerializedName("priceperday")
    private int priceperday;
    @SerializedName("model_year")
    private int model_year;
    @SerializedName("fuel_type")
    private String fuel_type;
    @SerializedName("seating_capacity")
    private int seating_capacity;
    @SerializedName("vimage1")
    private String vimage1;
    @SerializedName("driver_fees")
    private int driver_fees;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBooking_no() {
        return booking_no;
    }

    public void setBooking_no(int booking_no) {
        this.booking_no = booking_no;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public int getB_status() {
        return b_status;
    }

    public void setB_status(int b_status) {
        this.b_status = b_status;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public int getTotalnodays() {
        return totalnodays;
    }

    public void setTotalnodays(int totalnodays) {
        this.totalnodays = totalnodays;
    }

    public int getP_status() {
        return p_status;
    }

    public void setP_status(int p_status) {
        this.p_status = p_status;
    }

    public int getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(int amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getVehicle_title() {
        return vehicle_title;
    }

    public void setVehicle_title(String vehicle_title) {
        this.vehicle_title = vehicle_title;
    }

    public int getPriceperday() {
        return priceperday;
    }

    public void setPriceperday(int priceperday) {
        this.priceperday = priceperday;
    }

    public int getModel_year() {
        return model_year;
    }

    public void setModel_year(int model_year) {
        this.model_year = model_year;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public int getSeating_capacity() {
        return seating_capacity;
    }

    public void setSeating_capacity(int seating_capacity) {
        this.seating_capacity = seating_capacity;
    }

    public String getVimage1() {
        return vimage1;
    }

    public void setVimage1(String vimage1) {
        this.vimage1 = vimage1;
    }

    public int getDriver_fees() {
        return driver_fees;
    }

    public void setDriver_fees(int driver_fees) {
        this.driver_fees = driver_fees;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCadd() {
        return cadd;
    }

    public void setCadd(String cadd) {
        this.cadd = cadd;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCmail() {
        return cmail;
    }

    public void setCmail(String cmail) {
        this.cmail = cmail;
    }
}

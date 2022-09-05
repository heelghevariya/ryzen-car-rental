package com.example.ryzencarrent.Modals;

import com.google.gson.annotations.SerializedName;

public class CarModal implements Comparable<CarModal>{

    @SerializedName("vehicle_id")
    private int vehicle_id;

    @SerializedName("brand_id")
    private int brand_id;

    @SerializedName("vehicle_title")
    private String vehicle_title;

    @SerializedName("vehicle_no")
    private String vehicle_no;

    @SerializedName("vehicle_detail")
    private String vehicle_detail;

    @SerializedName("priceperday")
    private int priceperday;

    @SerializedName("fuel_type")
    private String fuel_type;

    @SerializedName("model_year")
    private int model_year;

    @SerializedName("seating_capacity")
    private String seating_capacity;

    @SerializedName("vimage1")
    private String vimage1;

    @SerializedName("vimage2")
    private String vimage2;
    @SerializedName("vimage3")
    private String vimage3;
    @SerializedName("vimage4")
    private String vimage4;

    @SerializedName("vimage5")
    private String vimage5;

    @SerializedName("Airconditioner")
    private int Airconditioner;
    @SerializedName("Childdoorlock")
    private int Childdoorlock;
    @SerializedName("Brakeassiste")
    private int Brakeassiste;
    @SerializedName("Driverairbage")
    private int Driverairbage;
    @SerializedName("Passengerairbage")
    private int Passengerairbage;
    @SerializedName("Powerwindow")
    private int Powerwindow;
    @SerializedName("Smartgps")
    private int Smartgps;
    @SerializedName("LEDdisplay")
    private int LEDdisplay;
    @SerializedName("Airfreshner")
    private int Airfreshner;
    @SerializedName("Auxcable")
    private int Auxcable;
    @SerializedName("Dashcam")
    private int Dashcam;
    @SerializedName("brand_name")
    private String brand_name;

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getVehicle_title() {
        return vehicle_title;
    }

    public void setVehicle_title(String vehicle_title) {
        this.vehicle_title = vehicle_title;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getVehicle_detail() {
        return vehicle_detail;
    }

    public void setVehicle_detail(String vehicle_detail) {
        this.vehicle_detail = vehicle_detail;
    }

    public int getPriceperday() {
        return priceperday;
    }

    public void setPriceperday(int priceperday) {
        this.priceperday = priceperday;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public int getModel_year() {
        return model_year;
    }

    public void setModel_year(int model_year) {
        this.model_year = model_year;
    }

    public String getSeating_capacity() {
        return seating_capacity;
    }

    public void setSeating_capacity(String seating_capacity) {
        this.seating_capacity = seating_capacity;
    }

    public String getVimage1() {
        return vimage1;
    }

    public void setVimage1(String vimage1) {
        this.vimage1 = vimage1;
    }

    public String getVimage2() {
        return vimage2;
    }

    public void setVimage2(String vimage2) {
        this.vimage2 = vimage2;
    }

    public String getVimage3() {
        return vimage3;
    }

    public void setVimage3(String vimage3) {
        this.vimage3 = vimage3;
    }

    public String getVimage4() {
        return vimage4;
    }

    public void setVimage4(String vimage4) {
        this.vimage4 = vimage4;
    }

    public String getVimage5() {
        return vimage5;
    }

    public void setVimage5(String vimage5) {
        this.vimage5 = vimage5;
    }

    public int getAirconditioner() {
        return Airconditioner;
    }

    public void setAirconditioner(int airconditioner) {
        Airconditioner = airconditioner;
    }

    public int getChilddoorlock() {
        return Childdoorlock;
    }

    public void setChilddoorlock(int childdoorlock) {
        Childdoorlock = childdoorlock;
    }

    public int getBrakeassiste() {
        return Brakeassiste;
    }

    public void setBrakeassiste(int brakeassiste) {
        Brakeassiste = brakeassiste;
    }

    public int getDriverairbage() {
        return Driverairbage;
    }

    public void setDriverairbage(int driverairbage) {
        Driverairbage = driverairbage;
    }

    public int getPassengerairbage() {
        return Passengerairbage;
    }

    public void setPassengerairbage(int passengerairbage) {
        Passengerairbage = passengerairbage;
    }

    public int getPowerwindow() {
        return Powerwindow;
    }

    public void setPowerwindow(int powerwindow) {
        Powerwindow = powerwindow;
    }

    public int getSmartgps() {
        return Smartgps;
    }

    public void setSmartgps(int smartgps) {
        Smartgps = smartgps;
    }

    public int getLEDdisplay() {
        return LEDdisplay;
    }

    public void setLEDdisplay(int LEDdisplay) {
        this.LEDdisplay = LEDdisplay;
    }

    public int getAirfreshner() {
        return Airfreshner;
    }

    public void setAirfreshner(int airfreshner) {
        Airfreshner = airfreshner;
    }

    public int getAuxcable() {
        return Auxcable;
    }

    public void setAuxcable(int auxcable) {
        Auxcable = auxcable;
    }

    public int getDashcam() {
        return Dashcam;
    }

    public void setDashcam(int dashcam) {
        Dashcam = dashcam;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    @Override
    public int compareTo(CarModal o) {
        int compareTo=((CarModal )o).getPriceperday();
        /* For Ascending order*/
        return this.priceperday-compareTo;

    }
}

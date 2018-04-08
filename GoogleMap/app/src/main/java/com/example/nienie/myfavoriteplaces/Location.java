package com.example.nienie.myfavoriteplaces;

/**
 * Created by nienie on 18/09/2017.
 */

public class Location {
    private int locationID;
    private int fldUserID;
    private String fldPlace;
    private String fldSubName;
    private double fldRate;
    private double fldLat;
    private double fldLong;
    private String fldAddress;
    private String fldType;

    public Location(int locationID,
                    int fldUserID,
                    String fldPlace,
                    String fldSubName,
                    double fldRate,
                    double fldLat,
                    double fldLong,
                    String fldAddress,
                    String fldType) {
        this.locationID = locationID;
        this.fldUserID = fldUserID;
        this.fldPlace = fldPlace;
        this.fldSubName = fldSubName;
        this.fldRate = fldRate;
        this.fldLat = fldLat;
        this.fldLong = fldLong;
        this.fldAddress = fldAddress;
        this.fldType = fldType;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getFldUserID() {
        return fldUserID;
    }

    public void setFldUserID(int fldUserID) {
        this.fldUserID = fldUserID;
    }

    public String getFldPlace() {
        return fldPlace;
    }

    public void setFldPlace(String fldPlace) {
        this.fldPlace = fldPlace;
    }

    public String getFldSubName() {
        return fldSubName;
    }

    public void setFldSubName(String fldSubName) {
        this.fldSubName = fldSubName;
    }

    public double getFldRate() {
        return fldRate;
    }

    public void setFldRate(double fldRate) {
        this.fldRate = fldRate;
    }

    public double getFldLat() {
        return fldLat;
    }

    public void setFldLat(double fldLat) {
        this.fldLat = fldLat;
    }

    public double getFldLong() {
        return fldLong;
    }

    public void setFldLong(double fldLong) {
        this.fldLong = fldLong;
    }

    public String getFldAddress() {
        return fldAddress;
    }

    public void setFldAddress(String fldAddress) {
        this.fldAddress = fldAddress;
    }

    public String getFldType() {
        return fldType;
    }

    public void setFldType(String fldType) {
        this.fldType = fldType;
    }
}

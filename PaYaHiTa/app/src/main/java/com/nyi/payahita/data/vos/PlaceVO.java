package com.nyi.payahita.data.vos;

/**
 * Created by IN-3442 on 27-Jul-16.
 */
public class PlaceVO {
    int id;
    String title;
    String division;
    String location;
    String phNo;
    String detail;

    public PlaceVO() {
    }

    public PlaceVO(int id, String title, String division, String location, String phNo, String detail) {
        this.id = id;
        this.title = title;
        this.division = division;
        this.location = location;
        this.phNo = phNo;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public PlaceVO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getPhNo() {
        return phNo;
    }

    public String getDetail() {
        return detail;
    }

    public String getDivision() {
        return division;
    }
}

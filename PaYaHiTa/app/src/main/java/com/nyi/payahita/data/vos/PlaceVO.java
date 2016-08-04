package com.nyi.payahita.data.vos;

import android.database.Cursor;

import com.nyi.payahita.data.persistence.PlaceContract;

/**
 * Created by IN-3442 on 27-Jul-16.
 */
public class PlaceVO {
    String id;
    String title;
    String division;
    String location;
    String phNo;
    String cost;
    String quantity;
    String detail;
    int isSaved;

    public PlaceVO() {
    }

    public PlaceVO(String title, String division, String location, String phNo, String cost, String quantity, String detail) {
        this.title = title;
        this.division = division;
        this.location = location;
        this.phNo = phNo;
        this.cost = cost;
        this.quantity = quantity;
        this.detail = detail;
    }

    public PlaceVO(String id, String title, String division, String location, String phNo, String cost, String quantity, String detail) {
        this.id = id;
        this.title = title;
        this.division = division;
        this.location = location;
        this.phNo = phNo;
        this.cost = cost;
        this.quantity = quantity;
        this.detail = detail;
    }

    public PlaceVO(String id, String title, String division, String location, String phNo, String cost, String quantity, String detail, int isSaved) {
        this.id = id;
        this.title = title;
        this.division = division;
        this.location = location;
        this.phNo = phNo;
        this.cost = cost;
        this.quantity = quantity;
        this.detail = detail;
        this.isSaved = isSaved;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getCost() {
        return cost;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getIsSaved() {
        return isSaved;
    }

    public static PlaceVO parseFromCursor(Cursor data) {
        PlaceVO placeVO = new PlaceVO(
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_ID)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_TITLE)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_DIVISION)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_LOCATION)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_PH_NO)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_COST)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_QUANTITY)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_DETAIL))
        );
        return placeVO;
    }
}

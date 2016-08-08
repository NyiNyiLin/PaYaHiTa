package com.nyi.payahita.data.vos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;

import com.nyi.payahita.data.persistence.PlaceContract;
import com.nyi.payahita.utils.Constants;

/**
 * Created by IN-3442 on 27-Jul-16.
 */
public class PlaceVO {
    String id;
    String title;
    int type;
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

    public PlaceVO(String id, String title, int type, String division, String location, String phNo, String cost, String quantity, String detail, int isSaved) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.division = division;
        this.location = location;
        this.phNo = phNo;
        this.cost = cost;
        this.quantity = quantity;
        this.detail = detail;
        this.isSaved = isSaved;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsSaved(int isSaved) {
        this.isSaved = isSaved;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
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
        int saved = Constants.UNSAVED;
        String isSaved = data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_IS_SAVED));
        if(isSaved != null) saved = Integer.parseInt(isSaved);

        PlaceVO placeVO = new PlaceVO(
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_ID)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_TITLE)),
                Integer.parseInt(data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_TYPE))),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_DIVISION)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_LOCATION)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_PH_NO)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_COST)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_QUANTITY)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_DETAIL)),
                saved
        );
        return placeVO;
    }

    public static ContentValues parseToContentValues(PlaceVO placeVO) {
        ContentValues cv = new ContentValues();
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_ID, placeVO.getId());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_TITLE, placeVO.getTitle());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_TYPE, placeVO.getType());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_DIVISION, placeVO.getDivision());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_LOCATION, placeVO.getLocation());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_PH_NO, placeVO.getPhNo());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_COST, placeVO.getCost());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_QUANTITY, placeVO.getQuantity());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_DETAIL, placeVO.getDetail());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_IS_SAVED, placeVO.getIsSaved());

        return cv;
    }
}

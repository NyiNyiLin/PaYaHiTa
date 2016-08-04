package com.nyi.payahita.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.data.persistence.PlaceContract;
import com.nyi.payahita.data.persistence.PlaceProvider;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 28-Jul-16.
 */
public class PlaceModel{
    private final String LOGTAG = "PlaceModel + ";

    private static PlaceModel objInstance;

    private List<PlaceVO> mPlaceList = new ArrayList<>();

    public PlaceModel(){
        //mPlaceList = UploadDataUtils.assignOfflineData();
    }

    public static PlaceModel getObjInstance(){
        if(objInstance == null){
            objInstance = new PlaceModel();
        }
        return objInstance;
    }

    public List<PlaceVO> getPlaceList() {
        return mPlaceList;
    }

    public PlaceVO getPlaceById(String id){
        for(PlaceVO placeVO : mPlaceList){
            if(placeVO.getId().compareTo(id) == 0) {
                Log.d(Constants.LOGTAG, LOGTAG + placeVO.getId() + " " +placeVO.getTitle());
                return placeVO;
            }
        }
        Log.d("tay lay", id);
        return new PlaceVO();
    }

    public void addNewPlace(PlaceVO placeVO){
        mPlaceList.add(placeVO);
    }

    public List<PlaceVO> update(PlaceVO placeVO, String id){
        PlaceVO placeVO1 = getPlaceById(id);
        int index = mPlaceList.indexOf(placeVO1);
        placeVO.setId(id);
        mPlaceList.remove(index);
        mPlaceList.add(index, placeVO);
        Toast.makeText(PaYaHiTa.getContext(), mPlaceList.size()+"", Toast.LENGTH_SHORT).show();
        return mPlaceList;
    }

    public void notifyPlaceLoaded(PlaceVO placeVO){
        mPlaceList.add(placeVO);

        Log.d(Constants.LOGTAG, LOGTAG + placeVO.getTitle());

        //keep the data in persistent layer.
        Context context = PaYaHiTa.getContext();

        Uri uri = context.getContentResolver().insert(PlaceContract.OrphanPlaceEntry.CONTENT_URI, parseToContentValues(placeVO));

        Log.d(Constants.LOGTAG, LOGTAG + uri);

    }

    private ContentValues parseToContentValues(PlaceVO placeVO) {
        ContentValues cv = new ContentValues();
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_ID, placeVO.getId());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_TITLE, placeVO.getTitle());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_DIVISION, placeVO.getDivision());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_LOCATION, placeVO.getLocation());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_PH_NO, placeVO.getPhNo());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_COST, placeVO.getCost());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_QUANTITY, placeVO.getQuantity());
        cv.put(PlaceContract.OrphanPlaceEntry.COLUMN_DETAIL, placeVO.getDetail());

        return cv;
    }

    public void notifyPlaceChange(PlaceVO placeVO) {
        //keep the data in persistent layer.
        Context context = PaYaHiTa.getContext();
        context.getContentResolver().update(PlaceContract.OrphanPlaceEntry.CONTENT_URI, parseToContentValues(placeVO), PlaceProvider.placeIDSelection, new String[]{placeVO.getId()});
    }
}

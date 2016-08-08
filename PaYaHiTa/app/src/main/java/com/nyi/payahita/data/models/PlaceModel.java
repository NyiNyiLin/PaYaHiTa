package com.nyi.payahita.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.data.persistence.PlaceContract;
import com.nyi.payahita.data.persistence.PlaceProvider;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.utils.Constants;

import java.net.URI;
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
        Log.d(Constants.LOGTAG, LOGTAG + id);
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

        Log.d(Constants.LOGTAG, LOGTAG + placeVO.getTitle() + placeVO.getType());

        //keep the data in persistent layer.
        Context context = PaYaHiTa.getContext();
        Uri uri = context.getContentResolver().insert(PlaceContract.OrphanPlaceEntry.CONTENT_URI, PlaceVO.parseToContentValues(placeVO));

        Log.d(Constants.LOGTAG, LOGTAG + uri);

    }

    public void notifyPlaceChange(PlaceVO placeVO) {
        //keep the data in persistent layer.
        Context context = PaYaHiTa.getContext();
        context.getContentResolver().update(PlaceContract.OrphanPlaceEntry.CONTENT_URI, PlaceVO.parseToContentValues(placeVO), PlaceProvider.placeIDSelection, new String[]{placeVO.getId()});
    }

    private Uri checkURI(int navigateTo){
        DatabaseReference mDatabaseReference;
        switch (navigateTo){
            case Constants.NAVIGATE_ORPHAN:
                return PlaceContract.OrphanPlaceEntry.CONTENT_URI;
            case Constants.NAVIGATE_NURSING_HOME:
                return PlaceContract.OrphanPlaceEntry.CONTENT_URI;
            default:
                return null;
        }
    }
}

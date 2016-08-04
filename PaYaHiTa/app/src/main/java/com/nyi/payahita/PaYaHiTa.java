package com.nyi.payahita;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.payahita.data.agents.Firebase;
import com.nyi.payahita.data.models.PlaceModel;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.utils.UploadDataUtils;

import java.util.List;

/**
 * Created by IN-3442 on 27-Jul-16.
 */
public class PaYaHiTa extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        PlaceModel.getObjInstance();

        //uploadData();
        Firebase.fetchData();

    }

    public static Context getContext() {
        return context;
    }

    public void uploadData(){

        List<PlaceVO> placeVOList = UploadDataUtils.assignData();

        for(PlaceVO placeVO: placeVOList){
            DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
            mDatabaseReference.child("orphan").push().setValue(placeVO);
        }
    }
}

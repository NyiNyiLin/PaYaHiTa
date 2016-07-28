package com.nyi.payahita;

import android.app.Application;
import android.content.Context;

import com.nyi.payahita.data.models.PlaceModel;

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
    }

    public static Context getContext() {
        return context;
    }
}

package com.nyi.payahita.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IN-3442 on 02-Aug-16.
 */
public class PlaceDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "place.db";

    private static final String SQL_CREATE_ORPHAN_PLACE_TABLE = "CREATE TABLE " + PlaceContract.OrphanPlaceEntry.TABLE_NAME + " (" +
            PlaceContract.OrphanPlaceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PlaceContract.OrphanPlaceEntry.COLUMN_ID + " TEXT NOT NULL, "+
            PlaceContract.OrphanPlaceEntry.COLUMN_TITLE + " TEXT NOT NULL, "+
            PlaceContract.OrphanPlaceEntry.COLUMN_DIVISION + " TEXT, "+
            PlaceContract.OrphanPlaceEntry.COLUMN_LOCATION + " TEXT, "+
            PlaceContract.OrphanPlaceEntry.COLUMN_PH_NO + " TEXT, "+
            PlaceContract.OrphanPlaceEntry.COLUMN_COST + " TEXT, "+
            PlaceContract.OrphanPlaceEntry.COLUMN_QUANTITY + " TEXT, "+
            PlaceContract.OrphanPlaceEntry.COLUMN_DETAIL + " TEXT, "+
            PlaceContract.OrphanPlaceEntry.COLUMN_IS_SAVED + " INTEGER, "+

            " UNIQUE (" + PlaceContract.OrphanPlaceEntry.COLUMN_ID + ") ON CONFLICT REPLACE" +
            " );";



    public PlaceDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ORPHAN_PLACE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PlaceContract.OrphanPlaceEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}

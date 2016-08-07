package com.nyi.payahita.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.nyi.payahita.PaYaHiTa;

/**
 * Created by IN-3442 on 02-Aug-16.
 */
public class PlaceContract {
    public static final String CONTENT_AUTHORITY = PaYaHiTa.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ORPHAN_PLACE = "orphanPlace";

    public static final class OrphanPlaceEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ORPHAN_PLACE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ORPHAN_PLACE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ORPHAN_PLACE;

        public static final String TABLE_NAME = "orphanPlace";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DIVISION = "division";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_PH_NO = "phno";
        public static final String COLUMN_COST = "cost";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_DETAIL = "detail";
        public static final String COLUMN_IS_SAVED = "saved";

        public static Uri buildAttractionUri(long id) {
            //content://com.nyi.payahita/place/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildOrphanPlaceUriWithID(String placeID) {
            //content://com.nyi.payahita/place?id="placeID"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_ID, placeID)
                    .build();
        }

        public static String getIDFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_ID);
        }


        public static Uri buildOrphanPlaceUriWithIsSaved(int isSaved) {
            //content://com.nyi.payahita/place?id="placeID"
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_IS_SAVED, isSaved + "")
                    .build();
        }

        public static String getIsSavedValueFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_IS_SAVED);
        }

    }
}

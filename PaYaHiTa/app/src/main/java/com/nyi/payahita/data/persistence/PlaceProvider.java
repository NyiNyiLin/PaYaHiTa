package com.nyi.payahita.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.nyi.payahita.utils.Constants;

/**
 * Created by IN-3442 on 02-Aug-16.
 */
public class PlaceProvider extends ContentProvider {
    public final String LOGTAG = "PlaceProvider + ";

    public static final int PLACE = 100;
    public static final String placeIDSelection = PlaceContract.OrphanPlaceEntry.COLUMN_ID + " = ?";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private PlaceDBHelper mPlaceDBHelper;

    @Override
    public boolean onCreate() {
        mPlaceDBHelper = new PlaceDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor queryCursor;

        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case PLACE:
                String placeID = PlaceContract.OrphanPlaceEntry.getIDFromParam(uri);
                if (!TextUtils.isEmpty(placeID)) {
                    selection = placeIDSelection;
                    selectionArgs = new String[]{placeID};
                    Log.d(Constants.LOGTAG, LOGTAG + placeID);
                }
                queryCursor = mPlaceDBHelper.getReadableDatabase().query(PlaceContract.OrphanPlaceEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);

        }

        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case PLACE:
                return PlaceContract.OrphanPlaceEntry.DIR_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mPlaceDBHelper.getWritableDatabase();
        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;

        switch (matchUri) {
            case PLACE: {
                long _id = db.insert(PlaceContract.OrphanPlaceEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = PlaceContract.OrphanPlaceEntry.buildAttractionUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mPlaceDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(PlaceContract.CONTENT_AUTHORITY, PlaceContract.PATH_ORPHAN_PLACE, PLACE);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case PLACE:
                return PlaceContract.OrphanPlaceEntry.TABLE_NAME;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}

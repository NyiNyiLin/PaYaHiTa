package com.nyi.payahita.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.adapters.PlaceAdapter;
import com.nyi.payahita.data.persistence.PlaceContract;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.utils.Constants;
import com.nyi.payahita.views.holders.PlaceViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, PlaceViewHolder.ControllerListItem{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_saved_item)
    RecyclerView rvSavedItem;

    private PlaceAdapter mPlaceAdapter;
    private List<PlaceVO> placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null ){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if(placeList == null) {
            placeList = new ArrayList<>();
        }
        placeList = new ArrayList<>();

        mPlaceAdapter =new PlaceAdapter(placeList, this);
        rvSavedItem.setAdapter(mPlaceAdapter);

        int gridColumnSpanCount = getResources().getInteger(R.integer.item_list_grid);
        rvSavedItem.setLayoutManager(new GridLayoutManager(PaYaHiTa.getContext(), gridColumnSpanCount));

        getSupportLoaderManager().initLoader(Constants.PLACE_SAVED_LIST_LOADER, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                PlaceContract.OrphanPlaceEntry.buildOrphanPlaceUriWithIsSaved(Constants.SAVED),
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            do {
                PlaceVO placeVO = PlaceVO.parseFromCursor(data);
                mPlaceAdapter.addNewPlace(placeVO);
            } while (data.moveToNext());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onTapListItemVO(PlaceVO placeVO, CardView cardView) {
        Intent intent = PlaceDetailActivity.newInstance(placeVO.getId());
        startActivity(intent);
    }
}

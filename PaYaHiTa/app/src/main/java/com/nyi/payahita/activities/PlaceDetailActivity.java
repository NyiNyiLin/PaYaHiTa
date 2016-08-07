package com.nyi.payahita.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.data.models.PlaceModel;
import com.nyi.payahita.data.persistence.PlaceContract;
import com.nyi.payahita.data.persistence.PlaceProvider;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import me.grantland.widget.AutofitHelper;

public class PlaceDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private final String LOGTAG = "PlaceDetailActivity";

    private static final String EXTRA_ID = "extra_id";
    public static final String TO_DETAIL_TRANSITION_NAME = "detail_transition";
    private String mPlaceID;
    private PlaceVO placeVO;

    private ActionBar actionBar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar1)
    Toolbar toolbar;

    @BindView(R.id.iv_place_image)
    ImageView ivPlaceImage;

    @BindView(R.id.tv_location_address)
    TextView tvLocationAddress;

    @BindView(R.id.tv_ph_no)
    TextView tvPhNo;

    @BindView(R.id.tv_desc_text)
    TextView tvDescText;

    @BindView(R.id.tv_daily_cost)
    TextView tvDailyCost;

    @BindView(R.id.tv_quantity)
    TextView tvQuantity;

    @BindView(R.id.tv_place_name)
    TextView tvToolBarTitle;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.cl_detail)
    NestedScrollView nestedScrollView;

    public static Intent newInstance(String id){
        Intent intent = new Intent(PaYaHiTa.getContext(), PlaceDetailActivity.class);
        intent.putExtra(EXTRA_ID, id);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

         actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            nestedScrollView.setTransitionName(TO_DETAIL_TRANSITION_NAME);
        }

        Intent intent = getIntent();
        mPlaceID = intent.getStringExtra(EXTRA_ID);

        //setData(id);

        getSupportLoaderManager().initLoader(Constants.PLACE_DETAIL_LOADER, null, this);

        //AutofitHelper.create(tvToolBarTitle);
    }

    private void setData(PlaceVO placeVO){
        if(placeVO != null){
            actionBar.setTitle(placeVO.getTitle());
            tvToolBarTitle.setText(placeVO.getTitle());

            tvLocationAddress.setText(placeVO.getLocation() + placeVO.getDivision());
            tvDescText.setText(placeVO.getDetail());
            tvPhNo.setText(placeVO.getPhNo());
            tvDailyCost.setText(placeVO.getCost());
            tvQuantity.setText(placeVO.getQuantity());

            if(placeVO.getIsSaved() == Constants.SAVED){
                fab.setImageResource(R.drawable.ic_favorite_black_24dp);
            }else {
                fab.setImageResource(R.drawable.ic_favorite_white_24dp);
            }
        }else throw new RuntimeException("There is no Place with given id");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(this,
                PlaceContract.OrphanPlaceEntry.buildOrphanPlaceUriWithID(mPlaceID),
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
             placeVO = PlaceVO.parseFromCursor(data);
            setData(placeVO);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @OnClick(R.id.fab)
    public void onClickFab(FloatingActionButton fab){
        if(placeVO.getIsSaved() == Constants.SAVED) {
            placeVO.setIsSaved(Constants.UNSAVED);
            fab.setImageResource(R.drawable.ic_favorite_white_24dp);
        }
        else {
            placeVO.setIsSaved(Constants.SAVED);
            fab.setImageResource(R.drawable.ic_favorite_black_24dp);
            Log.d(Constants.LOGTAG, LOGTAG + "Saved");
        }

        //keep the data in persistent layer.
        Context context = PaYaHiTa.getContext();
        context.getContentResolver().update(PlaceContract.OrphanPlaceEntry.CONTENT_URI, PlaceVO.parseToContentValues(placeVO), PlaceProvider.placeIDSelection, new String[]{placeVO.getId()});
    }
}

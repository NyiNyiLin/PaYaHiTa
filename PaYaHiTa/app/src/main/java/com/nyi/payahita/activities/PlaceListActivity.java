package com.nyi.payahita.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.adapters.PlaceAdapter;
import com.nyi.payahita.data.models.PlaceModel;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.views.holders.PlaceViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceListActivity extends AppCompatActivity implements PlaceViewHolder.ControllerListItem{
    @BindView(R.id.rv_item)
    RecyclerView rvItem;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    public static Intent newInstance(){
        Intent intent = new Intent(PaYaHiTa.getContext(), PlaceListActivity.class);
        return intent;
    }

    private PlaceAdapter mPlaceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<PlaceVO> placeList = PlaceModel.getObjInstance().getPlaceList();
        if(placeList == null) {
            placeList = new ArrayList<>();
        }

        mPlaceAdapter =new PlaceAdapter(placeList, this);
        rvItem.setAdapter(mPlaceAdapter);

        int gridColumnSpanCount = getResources().getInteger(R.integer.item_list_grid);
        rvItem.setLayoutManager(new GridLayoutManager(PaYaHiTa.getContext(), gridColumnSpanCount));
    }

    @Override
    public void onTapListItemVO(PlaceVO placeVO, ImageView imageView) {
        Intent intent = PlaceDetailActivity.newInstance(placeVO.getId());

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(imageView, PlaceDetailActivity.TO_DETAIL_TRANSITION_NAME));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }
}

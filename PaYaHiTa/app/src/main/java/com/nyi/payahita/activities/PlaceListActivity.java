package com.nyi.payahita.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.adapters.PlaceAdapter;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.views.holders.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceListActivity extends AppCompatActivity implements ItemViewHolder.ControllerListItem{
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

    private List<PlaceVO> placeVOList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPlaceAdapter =new PlaceAdapter(fakeData(), this);
        rvItem.setAdapter(mPlaceAdapter);

        int gridColumnSpanCount = getResources().getInteger(R.integer.item_list_grid);
        rvItem.setLayoutManager(new GridLayoutManager(PaYaHiTa.getContext(), gridColumnSpanCount));

    }

    private  List<PlaceVO> fakeData(){
        List<PlaceVO> placeVOList = new ArrayList<>();
        placeVOList.add(new PlaceVO("AAA"));
        placeVOList.add(new PlaceVO("AAA"));
        return placeVOList;
    }

    @Override
    public void onTapListItemVO(PlaceVO placeVO, ImageView imageView) {
        Intent intent = PlaceDetailActivity.newInstance(0);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(imageView, PlaceDetailActivity.TO_DETAIL_TRANSITION_NAME));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }
}

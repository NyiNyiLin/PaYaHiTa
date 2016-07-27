package com.nyi.payahita.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceDetailActivity extends AppCompatActivity {
    private static final String EXTRA_ID = "extra_id";
    public static final String TO_DETAIL_TRANSITION_NAME = "detail_transition";

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar1)
    Toolbar toolbar;

    @BindView(R.id.iv_place_image)
    ImageView ivPlaceImage;

    public static Intent newInstance(int id){
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            String transtionName = TO_DETAIL_TRANSITION_NAME;
            ivPlaceImage.setTransitionName(transtionName);
        }

        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID, 0);
    }
}

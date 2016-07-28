package com.nyi.payahita.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.payahita.R;
import com.nyi.payahita.activities.PlaceListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R.id.btn_orphantage)
    public void btnorphantage(View view){
        Intent intentToListActivity = PlaceListActivity.newInstance();
        startActivity(intentToListActivity);
    }

}

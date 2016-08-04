package com.nyi.payahita.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.adapters.PlaceAdapter;
import com.nyi.payahita.data.models.PlaceModel;
import com.nyi.payahita.data.vos.PlaceVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 30-Jul-16.
 */
public class PlaceListFragment1 extends Fragment {

    @BindView(R.id.rv_item)
    RecyclerView rvItem;

    private PlaceAdapter mPlaceAdapter;
    private List<PlaceVO> placeList;

    public static Fragment newInstance(){
        Fragment fragment = new PlaceListFragment1();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         placeList = PlaceModel.getObjInstance().getPlaceList();
        if(placeList == null) {
            placeList = new ArrayList<>();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_place_list, container, false);
        ButterKnife.bind(this, view);

        mPlaceAdapter =new PlaceAdapter(placeList, null);
        rvItem.setAdapter(mPlaceAdapter);

        int gridColumnSpanCount = getResources().getInteger(R.integer.item_list_grid);
        rvItem.setLayoutManager(new GridLayoutManager(PaYaHiTa.getContext(), gridColumnSpanCount));

        return view;

    }
}

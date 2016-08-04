package com.nyi.payahita.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.payahita.R;
import com.nyi.payahita.activities.PlaceListActivity;
import com.nyi.payahita.adapters.PlaceFragmentPagerAdapter;
import com.nyi.payahita.utils.MMFontUtils;
import com.nyi.payahita.views.holders.PlaceViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.tab_place)
    TabLayout tabPlaceLayout;

    @BindView(R.id.pager_place)
    ViewPager placeViewPager;

    PlaceViewHolder.ControllerListItem mControllerListItem;

    private PlaceFragmentPagerAdapter mPlaceFragmentPagerAdapter;

    public MainActivityFragment() {
    }

    public static Fragment newInstance(){
        Fragment fragment = new MainActivityFragment();
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerListItem = (PlaceViewHolder.ControllerListItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlaceFragmentPagerAdapter = new PlaceFragmentPagerAdapter(getActivity().getSupportFragmentManager());

        mPlaceFragmentPagerAdapter.addTab(new PlaceListFragment(), "asdfsfdf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        placeViewPager.setAdapter(mPlaceFragmentPagerAdapter);

        //to create all of the fragment
        placeViewPager.setOffscreenPageLimit(mPlaceFragmentPagerAdapter.getCount());

        //to connect with tab layout and view pager
        tabPlaceLayout.setupWithViewPager(placeViewPager);

        MMFontUtils.applyMMFontToTabLayout(tabPlaceLayout);

        return view;
    }

}

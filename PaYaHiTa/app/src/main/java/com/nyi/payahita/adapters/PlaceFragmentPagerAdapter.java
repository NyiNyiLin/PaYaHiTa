package com.nyi.payahita.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nyi.payahita.fragments.PlaceListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 30-Jul-16.
 */
public class PlaceFragmentPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> mFragments = new ArrayList<>();
    List<String> mFragmentsTitle = new ArrayList<>();

    public PlaceFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsTitle.get(position);
    }

    public void addTab(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentsTitle.add(title);
    }
}

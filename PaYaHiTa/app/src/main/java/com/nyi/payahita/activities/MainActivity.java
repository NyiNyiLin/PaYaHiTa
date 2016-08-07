package com.nyi.payahita.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.adapters.PlaceFragmentPagerAdapter;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.fragments.MainActivityFragment;
import com.nyi.payahita.fragments.PlaceListFragment;
import com.nyi.payahita.utils.MMFontUtils;
import com.nyi.payahita.views.holders.PlaceViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PlaceViewHolder.ControllerListItem, NavigationView.OnNavigationItemSelectedListener{

    private final String LOGTAG = "MainActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tab_place)
    TabLayout tabPlaceLayout;

    @BindView(R.id.pager_place)
    ViewPager placeViewPager;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    private PlaceFragmentPagerAdapter mPlaceFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        }

        //Bind listener to navigation view
        mNavigationView.setNavigationItemSelectedListener(this);

        //Bind Fragment
        mPlaceFragmentPagerAdapter = new PlaceFragmentPagerAdapter(getSupportFragmentManager());

        mPlaceFragmentPagerAdapter.addTab(PlaceListFragment.newInstance(), getResources().getString(R.string.title_orphan));
        mPlaceFragmentPagerAdapter.addTab(new Fragment(), getResources().getString(R.string.title_nursing_home));
        mPlaceFragmentPagerAdapter.addTab(new Fragment(), getResources().getString(R.string.title_pa_ya_hi_ta));
        mPlaceFragmentPagerAdapter.addTab(new Fragment(), getResources().getString(R.string.title_ba_ka));
        mPlaceFragmentPagerAdapter.addTab(new Fragment(), getResources().getString(R.string.title_ti_la_shin));

        placeViewPager.setAdapter(mPlaceFragmentPagerAdapter);

        //to create all of the fragment
        placeViewPager.setOffscreenPageLimit(mPlaceFragmentPagerAdapter.getCount());

        //to connect with tab layout and view pager
        tabPlaceLayout.setupWithViewPager(placeViewPager);

        MMFontUtils.applyMMFontToTabLayout(tabPlaceLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapListItemVO(PlaceVO placeVO, CardView cardView) {
        Intent intent = PlaceDetailActivity.newInstance(placeVO.getId());
        //startActivity(intent);
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(cardView, PlaceDetailActivity.TO_DETAIL_TRANSITION_NAME));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }

    @OnClick(R.id.fab)
    public void onCLickFab(View view){
        Intent intent = NewEditActivity.newInstance();
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}

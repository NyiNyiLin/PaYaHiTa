package com.nyi.payahita.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

/**
 * Created by IN-3442 on 30-Jul-16.
 */
public class PlaceListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private final String LOG = "PlaceListFragment";
    private static final String NAVIGATESTRING = "navigate";
    private int mNavigateTo;

    @BindView(R.id.rv_item)
    RecyclerView rvItem;

    private PlaceAdapter mPlaceAdapter;
    private List<PlaceVO> placeList;
    private PlaceViewHolder.ControllerListItem mControllerListItem;


    public static Fragment newInstance(int NavigateTo){
        Fragment fragment = new PlaceListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(NAVIGATESTRING, NavigateTo);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static Fragment newInstance2(int NavigateTo){
        Fragment fragment = new PlaceListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(NAVIGATESTRING, NavigateTo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mControllerListItem = (PlaceViewHolder.ControllerListItem) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(mNavigateTo, null, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         //placeList = PlaceModel.getObjInstance().getPlaceList();
        if(placeList == null) {
            placeList = new ArrayList<>();
        }
        placeList = new ArrayList<>();

        Bundle bundle = getArguments();
        mNavigateTo = bundle.getInt(NAVIGATESTRING);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_placelist, container, false);
        ButterKnife.bind(this, view);

        mPlaceAdapter =new PlaceAdapter(placeList, mControllerListItem);
        rvItem.setAdapter(mPlaceAdapter);


        int gridColumnSpanCount = getResources().getInteger(R.integer.item_list_grid);
        rvItem.setLayoutManager(new GridLayoutManager(PaYaHiTa.getContext(), gridColumnSpanCount));

        //Firebase.fetchDta(mPlaceAdapter);

        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                PlaceContract.OrphanPlaceEntry.buildOrphanPlaceUriWithType(mNavigateTo),
                null,
                null,
                null,
                PlaceContract.OrphanPlaceEntry.COLUMN_ID + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<PlaceVO> placeVOList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                PlaceVO placeVO = PlaceVO.parseFromCursor(data);
                placeVOList.add(placeVO);
            } while (data.moveToNext());
        }

        Log.d(LOG, "Retrieved place DESC : " + placeVOList.size());
        if(placeVOList.size() != 0)mPlaceAdapter.addAllList(placeVOList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private Uri getQueryUri(int navigateTo){
        switch (navigateTo){
            case Constants.NAVIGATE_ORPHAN:
                return PlaceContract.OrphanPlaceEntry.buildOrphanPlaceUriWithType(navigateTo);
            case Constants.NAVIGATE_NURSING_HOME:
                return PlaceContract.OrphanPlaceEntry.buildOrphanPlaceUriWithType(navigateTo);
            default: return null;
        }
    }

}

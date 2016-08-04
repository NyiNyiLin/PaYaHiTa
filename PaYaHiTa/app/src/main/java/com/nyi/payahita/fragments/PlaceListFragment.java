package com.nyi.payahita.fragments;

import android.content.Context;
import android.database.Cursor;
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

    @BindView(R.id.rv_item)
    RecyclerView rvItem;

    private PlaceAdapter mPlaceAdapter;
    private List<PlaceVO> placeList;
    private PlaceViewHolder.ControllerListItem mControllerListItem;


    public static Fragment newInstance(){
        Fragment fragment = new PlaceListFragment();
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
        getActivity().getSupportLoaderManager().initLoader(Constants.PLACE_LIST_LOADER, null, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         //placeList = PlaceModel.getObjInstance().getPlaceList();
        if(placeList == null) {
            placeList = new ArrayList<>();
        }
        placeList = new ArrayList<>();
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
                PlaceContract.OrphanPlaceEntry.CONTENT_URI,
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
                PlaceVO placeVO = parseFromCursor(data);
                placeVOList.add(placeVO);
            } while (data.moveToNext());
        }

        Log.d(LOG, "Retrieved place DESC : " + placeVOList.size());
        if(placeVOList.size() != 0)mPlaceAdapter.addAllList(placeVOList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public static PlaceVO parseFromCursor(Cursor data) {
        PlaceVO placeVO = new PlaceVO(
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_ID)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_TITLE)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_DIVISION)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_LOCATION)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_PH_NO)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_COST)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_QUANTITY)),
                data.getString(data.getColumnIndex(PlaceContract.OrphanPlaceEntry.COLUMN_DETAIL))
        );
        return placeVO;
    }
}

package com.nyi.payahita.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.views.holders.PlaceViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 27-Jul-16.
 */
public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> {
    private List<PlaceVO> mItemList;
    private LayoutInflater inflater;
    private PlaceViewHolder.ControllerListItem mControllerListItem;

    public PlaceAdapter(List<PlaceVO> placeVOList, PlaceViewHolder.ControllerListItem controllerListItem) {
        this.mItemList = placeVOList;
        mControllerListItem = controllerListItem;
        inflater = LayoutInflater.from(PaYaHiTa.getContext());
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_place_item, parent, false);
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(view, mControllerListItem);
        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.bindItem(mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}

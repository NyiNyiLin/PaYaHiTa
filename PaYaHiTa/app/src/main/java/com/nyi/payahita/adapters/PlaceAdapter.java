package com.nyi.payahita.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.views.holders.ItemViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 27-Jul-16.
 */
public class PlaceAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<PlaceVO> mItemList;
    private LayoutInflater inflater;
    private ItemViewHolder.ControllerListItem mControllerListItem;

    public PlaceAdapter(List<PlaceVO> placeVOList, ItemViewHolder.ControllerListItem controllerListItem) {
        this.mItemList = placeVOList;
        mControllerListItem = controllerListItem;
        inflater = LayoutInflater.from(PaYaHiTa.getContext());
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_place_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view, mControllerListItem);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bindItem(mItemList.get(0));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}

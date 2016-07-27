package com.nyi.payahita.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.payahita.R;
import com.nyi.payahita.data.vos.PlaceVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 27-Jul-16.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_place_name)
    TextView tvPlaceName;

    @BindView(R.id.iv_place_image)
    ImageView ivPlaceImage;

    private PlaceVO placeVO;
    private ControllerListItem mControllerListItem;

    public ItemViewHolder(View itemView, ControllerListItem controllerListItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mControllerListItem = controllerListItem;
    }

    public void bindItem(PlaceVO placeVO){
        this.placeVO = placeVO;
        tvPlaceName.setText(placeVO.getTitle());
    }

    @Override
    public void onClick(View view) {
        mControllerListItem.onTapListItemVO(placeVO, ivPlaceImage);
    }

    public interface ControllerListItem{
        void onTapListItemVO(PlaceVO placeVO, ImageView imageView);
    }
}

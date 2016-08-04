package com.nyi.payahita.views.holders;

import android.support.v7.widget.CardView;
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
public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.cl_detail)
    CardView cardView;

    @BindView(R.id.tv_place_name)
    TextView tvPlaceName;

    @BindView(R.id.tv_place_division)
    TextView tvPlaceDivision;

    @BindView(R.id.iv_place_image)
    ImageView ivPlaceImage;

    @BindView(R.id.tv_place_desc)
    TextView tvPlaceDesc;

    private PlaceVO placeVO;
    private ControllerListItem mControllerListItem;

    public PlaceViewHolder(View itemView, ControllerListItem controllerListItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mControllerListItem = controllerListItem;
    }

    public void bindItem(PlaceVO placeVO){
        this.placeVO = placeVO;
        tvPlaceName.setText(placeVO.getTitle());
        tvPlaceDivision.setText(placeVO.getDivision());
        tvPlaceDesc.setText(placeVO.getDetail());
    }

    @Override
    public void onClick(View view) {
        mControllerListItem.onTapListItemVO(placeVO, cardView);
    }

    public interface ControllerListItem{
        void onTapListItemVO(PlaceVO placeVO, CardView cardView);
    }
}

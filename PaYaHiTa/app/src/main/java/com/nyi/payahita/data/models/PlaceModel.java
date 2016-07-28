package com.nyi.payahita.data.models;

import com.nyi.payahita.data.vos.PlaceVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 28-Jul-16.
 */
public class PlaceModel{
    private static PlaceModel objInstance;

    private List<PlaceVO> mPlaceList;

    public PlaceModel(){
        mPlaceList = fakeData();
    }

    public static PlaceModel getObjInstance(){
        if(objInstance == null){
            objInstance = new PlaceModel();
        }
        return objInstance;
    }

    private  List<PlaceVO> fakeData(){
        List<PlaceVO> placeVOList = new ArrayList<>();
        placeVOList.add(new PlaceVO(0, "AAA", "Yangon", "No24, 44st, Botahtaung", "09254126854", "blah blah blah"));
        placeVOList.add(new PlaceVO(1, "BBB", "Yangon", "No24, 44st, Botahtaung", "09254126854", "blah blah blah"));
        placeVOList.add(new PlaceVO(2, "CCC", "Yangon", "No24, 44st, Botahtaung", "09254126854", "blah blah blah"));
        placeVOList.add(new PlaceVO(3, "DDD", "Yangon", "No24, 44st, Botahtaung", "09254126854", "blah blah blah"));
        placeVOList.add(new PlaceVO(4, "EEE", "Yangon", "No24, 44st, Botahtaung", "09254126854", "blah blah blah"));
        placeVOList.add(new PlaceVO(5, "FFF", "Yangon", "No24, 44st, Botahtaung", "09254126854", "blah blah blah"));
        placeVOList.add(new PlaceVO(6, "GGG", "Yangon", "No24, 44st, Botahtaung", "09254126854", "blah blah blah"));
        placeVOList.add(new PlaceVO(7, "HHH", "Yangon", "No24, 44st, Botahtaung", "09254126854", "blah blah blah"));


        return placeVOList;
    }

    public List<PlaceVO> getPlaceList() {
        return mPlaceList;
    }

    public PlaceVO getPlaceById(int id){
        for(PlaceVO placeVO : mPlaceList){
            if(placeVO.getId() == id) return placeVO;
        }
        return new PlaceVO();
    }

}

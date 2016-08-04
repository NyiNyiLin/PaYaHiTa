package com.nyi.payahita.data.agents;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.payahita.adapters.PlaceAdapter;
import com.nyi.payahita.data.models.PlaceModel;
import com.nyi.payahita.data.vos.PlaceVO;

import java.util.List;

/**
 * Created by IN-3442 on 02-Aug-16.
 */
public class Firebase {

    public static void fetchDta(final PlaceAdapter placeAdapter){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("orphan");


        //databaseReference.addValueEventListener()

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                PlaceVO placeVO = dataSnapshot.getValue(PlaceVO.class);
                placeVO.setId(dataSnapshot.getKey());

                //This is for direct insert
                /*PlaceModel.getObjInstance().addNewPlace(placeVO);
                placeAdapter.addNewPlace(placeVO);*/

                //Using Content Provider
                PlaceModel.getObjInstance().notifyPlaceLoaded(placeVO);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                PlaceVO placeVO = dataSnapshot.getValue(PlaceVO.class);

                List<PlaceVO> placeVOList = PlaceModel.getObjInstance().update(placeVO, dataSnapshot.getKey());

                placeAdapter.addAllList(placeVOList);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                PlaceVO placeVO = dataSnapshot.getValue(PlaceVO.class);
                PlaceModel.getObjInstance().getPlaceList().remove(placeVO);
                placeAdapter.removePlace(placeVO);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void fetchData(){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("orphan");


        //databaseReference.addValueEventListener()

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                PlaceVO placeVO = dataSnapshot.getValue(PlaceVO.class);
                placeVO.setId(dataSnapshot.getKey());

                //This is for direct insert
                /*PlaceModel.getObjInstance().addNewPlace(placeVO);
                placeAdapter.addNewPlace(placeVO);*/

                //Using Content Provider
                PlaceModel.getObjInstance().notifyPlaceLoaded(placeVO);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                PlaceVO placeVO = dataSnapshot.getValue(PlaceVO.class);
                placeVO.setId(dataSnapshot.getKey());

                //Using Content Provider
                PlaceModel.getObjInstance().notifyPlaceChange(placeVO);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

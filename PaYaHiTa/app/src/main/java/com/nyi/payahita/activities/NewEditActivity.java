package com.nyi.payahita.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nyi.payahita.PaYaHiTa;
import com.nyi.payahita.R;
import com.nyi.payahita.data.agents.Firebase;
import com.nyi.payahita.data.vos.PlaceVO;
import com.nyi.payahita.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewEditActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_division)
    EditText etDivision;

    @BindView(R.id.et_location)
    EditText etLocation;

    @BindView(R.id.et_phno)
    EditText etPhNo;

    @BindView(R.id.et_today_cost)
    EditText etTodayCost;

    @BindView(R.id.et_total_people)
    EditText etTotalPeople;

    @BindView(R.id.et_desc)
    EditText etDesc;

    @BindView(R.id.spinner)
    Spinner spinner;

    //private String[] type = new String[5];
    Context context = PaYaHiTa.getContext();
    private String[] type = {context.getString(R.string.title_orphan), context.getString(R.string.title_nursing_home), context.getString(R.string.title_pa_ya_hi_ta), context.getString(R.string.title_ba_ka), context.getString(R.string.title_ti_la_shin),};
    private int[] positionType = {Constants.NAVIGATE_ORPHAN, Constants.NAVIGATE_NURSING_HOME, Constants.NAVIGATE_PAYAHITA, Constants.NAVIGATE_BA_KA, Constants.NAVIGATE_TI_LA_SHIN};
    private int navigateTo = Constants.NAVIGATE_ORPHAN;

    public static Intent newInstance(){
        Intent intent = new Intent(PaYaHiTa.getContext(), NewEditActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_edit);

        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        //For spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplication(), R.layout.spinner_dropdown_list, type);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_list);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                navigateTo = positionType[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @OnClick(R.id.fab)
    public void onClickSave(View view){
        if(etName.getText().toString().isEmpty() ) {
            etName.setError("Name can't be blank");
        }
        else if(etLocation.getText().toString().isEmpty()){
            etLocation.setError("Location can't be blank");
        }
        else if(etDivision.getText().toString().isEmpty()){
            etDivision.setError("Division can't be blank");
        }
        else {
            String name = etName.getText().toString();
            String divsion = etDivision.getText().toString();
            String location = etLocation.getText().toString();
            String phno = etPhNo.getText().toString();
            String todayCost = etTodayCost.getText().toString();
            String totalPerson = etTotalPeople.getText().toString();
            String desc = etDesc.getText().toString();

            PlaceVO placeVO = new PlaceVO(name, divsion, location, phno, todayCost, totalPerson, desc);
            Firebase.uploadNewPlace(navigateTo, placeVO);

            this.finish();
        }
    }


}

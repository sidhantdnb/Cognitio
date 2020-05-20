package com.example.harshit.cognitio19.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.harshit.cognitio19.Adapters.EventAdapterView;
import com.example.harshit.cognitio19.Modals.Modal;
import com.example.harshit.cognitio19.R;
import com.example.harshit.cognitio19.Utils.Constants;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class Events extends AppCompatActivity implements View.OnClickListener{

    ImageView back;RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        MainActivity.isWarningShown=false;
        rv = findViewById(R.id.rv_events_new);
        back=findViewById(R.id.back_arrow);
        back.setOnClickListener(this);
        rv.setHasFixedSize(true);
        LinearLayoutManager lm=new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(new EventAdapterView(this,prepareEventList()));
    }

    private ArrayList<Modal> prepareEventList() {
        ArrayList<Modal> modals=new ArrayList<>();
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[0],Constants.EVENT_DESCRIPTION[0],"Prizes worth: \n\u20B94,400*",Constants.EVENT_NAMES[0]));//Radiation
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[1],Constants.EVENT_DESCRIPTION[1],"Prizes worth: \n\u20B92,100*",Constants.EVENT_NAMES[1]));//wit to veto
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[2],Constants.EVENT_DESCRIPTION[2],"Prizes worth: \n\u20B93,400*",Constants.EVENT_NAMES[2]));//Dictum Symposium
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[3],Constants.EVENT_DESCRIPTION[3],"Prizes worth: \n\u20B93,400*",Constants.EVENT_NAMES[3]));//conundrum
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[4],Constants.EVENT_DESCRIPTION[4],"Prizes worth: \n\u20B93,700*",Constants.EVENT_NAMES[4]));//TIE the KNOT
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[5],Constants.EVENT_DESCRIPTION[5],"Prizes worth: \n\u20B92,100*",Constants.EVENT_NAMES[5]));//Place Station
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[6],Constants.EVENT_DESCRIPTION[6],"Prizes worth: \n\u20B93,400*",Constants.EVENT_NAMES[6]));//Cansys
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[7],Constants.EVENT_DESCRIPTION[7],"Prizes worth: \n\u20B94,500*",Constants.EVENT_NAMES[7]));//Assemblage
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[8],Constants.EVENT_DESCRIPTION[8],"Prizes worth: \n\u20B93,400*",Constants.EVENT_NAMES[8]));//Quriosity
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[9],Constants.EVENT_DESCRIPTION[9],"Prizes worth: \n\u20B94,500*",Constants.EVENT_NAMES[9]));//Shoot at Sight
        modals.add(new Modal(Constants.EVENT_IMAGE_ONLINE[10],Constants.EVENT_DESCRIPTION[10],"Prizes worth: \n\u20B93,400*",Constants.EVENT_NAMES[10]));//corporate bytes
        return modals;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back_arrow){
            finish();
        }
    }
}

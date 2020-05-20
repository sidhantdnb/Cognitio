package com.example.harshit.cognitio19.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.harshit.cognitio19.Adapters.SponsorsAdapter;
import com.example.harshit.cognitio19.Modals.TeamModal;
import com.example.harshit.cognitio19.R;
import com.example.harshit.cognitio19.Utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sponsors extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rv;ImageView back;
    SponsorsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        rv=findViewById(R.id.rv_sponsors);
        back=findViewById(R.id.back_arrow);
        back.setOnClickListener(this);
        MainActivity.isWarningShown=false;

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SponsorsAdapter(this,prepareDataList());
        rv.setAdapter(adapter);
    }

    private ArrayList<TeamModal> prepareDataList() {
        final ArrayList<TeamModal> dataList=new ArrayList<>();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_REF_SPONSORS);
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String image,desc;
                dataList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    image=ds.child("Image").getValue().toString();
                    desc=ds.child("Desc").getValue().toString();
                    dataList.add(new TeamModal(image,desc));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return dataList;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back_arrow)finish();
    }
}

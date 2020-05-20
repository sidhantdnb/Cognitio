package com.example.harshit.cognitio19.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshit.cognitio19.Adapters.ExpandableListAdapter;
import com.example.harshit.cognitio19.Modals.ExpandableListModal;
import com.example.harshit.cognitio19.R;
import com.example.harshit.cognitio19.Utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FAQ_NOTIF extends AppCompatActivity implements View.OnClickListener {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    static ArrayList<ExpandableListModal> listData_Notifs;
    static ArrayList<ExpandableListModal> listData_FAQs;
    ImageView back;TextView page_title;
    int grp_expanded=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_notif);

        back=findViewById(R.id.back_arrow);
        back.setOnClickListener(this);
        MainActivity.isWarningShown=false;

        listData_FAQs = new ArrayList<>();
        listData_Notifs=new ArrayList<>();

        page_title=findViewById(R.id.spinner);
        page_title.setText(getIntent().getExtras().getString("Button_Pressed"));

        // get the listview
        expListView =findViewById(R.id.lvExp);

        listAdapter = new ExpandableListAdapter(this, prepareListData());

        // setting list adapter
        expListView.setAdapter(listAdapter);
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if(grp_expanded!=-1&&grp_expanded!=groupPosition){
                    expListView.collapseGroup(grp_expanded);
                }
                grp_expanded=groupPosition;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private ArrayList<ExpandableListModal> prepareListData() {

        if(getIntent().getExtras().getString("Button_Pressed").equals("FAQs")){


            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                                                    .child(Constants.FIREBASE_REF_FAQS);
            databaseReference.keepSynced(true);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listData_FAQs.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        ExpandableListModal faqs = ds.getValue(ExpandableListModal.class);
                        listData_FAQs.add(faqs);
                        listAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            return listData_FAQs;
        }

        if(getIntent().getExtras().getString("Button_Pressed").equals("Notifications")){

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                    .child(Constants.FIREBASE_REF_NOTIFICATIONS);
            databaseReference.keepSynced(true);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listData_Notifs.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        ExpandableListModal faqs = ds.getValue(ExpandableListModal.class);
                        listData_Notifs.add(faqs);
                        listAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            return listData_Notifs;
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back_arrow) finish();
    }

    public ArrayList<ExpandableListModal> reverse(ArrayList<ExpandableListModal> list) {
        if(list.size() > 1) {
            ExpandableListModal value = list.remove(0);
            reverse(list);
            list.add(value);
        }
        return list;
    }

}

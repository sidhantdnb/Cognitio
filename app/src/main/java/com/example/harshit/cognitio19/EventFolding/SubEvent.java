package com.example.harshit.cognitio19.EventFolding;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.desarrollodroide.libraryfragmenttransactionextended.FragmentTransactionExtended;
import com.example.harshit.cognitio19.R;

public class SubEvent extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private TextView event_title;

    private About fragment_about;
    private Details fragment_details;
    private Prizes fragment_prizes;
    private Coordinator fragment_coordinator;
    public static int position;

    private FrameLayout parent_layout;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event);
        bottomNavigationView=findViewById(R.id.bottom_nav);
        event_title=findViewById(R.id.spinner);
        parent_layout=findViewById(R.id.parent_layout);

        fragment_about=new About();
        fragment_coordinator=new Coordinator();
        fragment_details=new Details();
        fragment_prizes=new Prizes();
        menu=bottomNavigationView.getMenu();

        event_title.setText(getIntent().getExtras().getString("EventName"));
        position=getIntent().getExtras().getInt("EventPosition");

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        android.app.FragmentManager fragmentManager=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.parent_layout,fragment_about).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.about_menu:
                setFragment(fragment_about);
                return true;
            case R.id.details_menu:
                setFragment(fragment_details);
                return true;
            case R.id.coordinator_menu:
                setFragment(fragment_coordinator);
                return true;
            default:
                return true;
        }
    }

    private void setFragment(android.app.Fragment fragment) {
        android.app.FragmentManager fragmentManager=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.parent_layout,fragment).commit();
        FragmentTransactionExtended fragmentTransactionExtended =
                new FragmentTransactionExtended(this,fragmentTransaction,new About(),fragment,R.id.parent_layout);
        fragmentTransactionExtended.addTransition(FragmentTransactionExtended.TABLE_HORIZONTAL);
        fragmentTransactionExtended.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

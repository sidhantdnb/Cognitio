package com.example.harshit.cognitio19.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshit.cognitio19.Adapters.CoreTeamAdapter;
import com.example.harshit.cognitio19.Modals.TeamModal;
import com.example.harshit.cognitio19.R;
import com.example.harshit.cognitio19.Utils.Communicator;
import com.example.harshit.cognitio19.Utils.DialogList;
import com.example.harshit.cognitio19.Utils.DialogPop;

import java.util.ArrayList;

public class CoreTeam extends AppCompatActivity implements View.OnClickListener,Communicator{

    RecyclerView recyclerView;ImageView back;
    FragmentManager manager;
    TextView portfolio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_team);
        recyclerView=findViewById(R.id.rv_team);
        back=findViewById(R.id.back_arrow);
        portfolio=findViewById(R.id.portfolio);
        manager=getSupportFragmentManager();
        back.setOnClickListener(this);
        MainActivity.isWarningShown=false;

        recyclerView.setAdapter(new CoreTeamAdapter(this,setAdministration()));
        FragmentTransaction transaction=manager.beginTransaction();
        DialogList dialogList=new DialogList();
        transaction.add(dialogList,"dialogList");
        transaction.commit();
    }

    private void prepareDataList(int position) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        switch (position){
            case 0:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setAdministration()));break;
            case 1:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setCore()));break;
            case 2:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setPlanning()));break;
            case 3:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setPublicRelations()));break;
            case 4:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setAppDev()));break;
            case 5:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setManagement()));break;
            case 6:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setCA()));break;
            case 7:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setCreative()));break;
            case 8:
                recyclerView.setAdapter(new CoreTeamAdapter(this,setLiterary()));break;
        }
    }

    private ArrayList<TeamModal> setCore() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Rohit Kumar Singh","President",R.drawable.rohit,"7260055926",null));
        memberList.add(new TeamModal("Gopal Agrawal","General Secretary",R.drawable.gopal,"9955268864",null));
        memberList.add(new TeamModal("Pranjal Saxena","Spokesperson",R.drawable.pranjal,"9608197819",null));
        memberList.add(new TeamModal("Bhawesh Kumar","Joint Secretary",R.drawable.bhawesh,"8051011891",null));
        return memberList;
    }
    private ArrayList<TeamModal> setAdministration() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Prof. K.K. Shukla","Director",R.drawable.director,null,"NIT Jamshedpur"));
        memberList.add(new TeamModal("Prof. M.K. Paswan","HOD",R.drawable.hod,null,"Mechanical Dept."));
        memberList.add(new TeamModal("Prof. S Kumar","Professor Incharge",R.drawable.facultyadvisor,null,"Cognitio"));
        return memberList;
    }
    private ArrayList<TeamModal> setPlanning() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Ravi Kumar Singh","Planning Head",R.drawable.ravi,"9931408722",null));
        memberList.add(new TeamModal("Ch. Likhitesh","Planning Head",R.drawable.likhitesh,"8463900727",null));
        return memberList;
    }
    private ArrayList<TeamModal> setLiterary() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Vishal Jha","Senior Co-ordinator",R.drawable.vishal_jha,"8271719884",null));
        memberList.add(new TeamModal("Pranav Prakash","Co-ordinator",R.drawable.pranav_prakash,"7209669436",null));
        return memberList;
    }
    private ArrayList<TeamModal> setCA() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Bijay Mahapatra","Co-ordinator",R.drawable.bijay,"8235698592",null));
        memberList.add(new TeamModal("Vaibhaav Srinath Dev","Co-ordinator",R.drawable.vaibhav,"9566137756",null));
        return memberList;
    }
    private ArrayList<TeamModal> setCreative() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Abhijeet Gorai","Creative Head",R.drawable.abhijeet,"9097830275",null));
        memberList.add(new TeamModal("Sudhanshu Prakash","Co-ordinator",R.drawable.danton,"8862927236",null));
        memberList.add(new TeamModal("Pratik Singh","Co-ordinator",R.drawable.pratik,"7000877611",null));
        memberList.add(new TeamModal("Shatanik Bose","Co-ordinator",R.drawable.santanik,"7654974223",null));
        return memberList;
    }
    private ArrayList<TeamModal> setAppDev() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Harshit Gupta","App Head",R.drawable.harshit,"7488128330",null));
        memberList.add(new TeamModal("Rishikesh Sengor","Designer",R.drawable.rishi,"7295954464",null));
        return memberList;
    }
    private ArrayList<TeamModal> setManagement() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Anup Chaki","Management Head",R.drawable.anup,"9613370060",null));
        memberList.add(new TeamModal("D. Sri. Lakshmikanth","Management Head",R.drawable.lakshmikant,"7732017542",null));
        memberList.add(new TeamModal("S. Surya Krishna","Management Head",R.drawable.surya,"9515282601",null));
        memberList.add(new TeamModal("T. Viswa Narayan","Management Head",R.drawable.viswa,"9515324204",null));
        memberList.add(new TeamModal("Saim Hussain","Anchoring Head",R.drawable.saim,"9661541096",null));
        return memberList;
    }
    private ArrayList<TeamModal> setPublicRelations() {
        ArrayList<TeamModal> memberList=new ArrayList<>();
        memberList.add(new TeamModal("Jay Anand","PR Head",R.drawable.jay,"8210103171",null));
        memberList.add(new TeamModal("Vikram Kumar","PR Head",R.drawable.vikram,"9661660522",null));
        memberList.add(new TeamModal("Manish Mishra","Senior Co-ordinator",R.drawable.manish,"7004575192",null));
        memberList.add(new TeamModal("Onkar Kumar","Co-ordinator",R.drawable.onkar,"9472903552",null));
        memberList.add(new TeamModal("Dheeraj Singh","Co-ordinator",R.drawable.dheeraj,"7903535578",null));
        return memberList;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_arrow:
                FragmentTransaction transaction=manager.beginTransaction();
                DialogList dialogList=new DialogList();
                transaction.add(dialogList,"dialogList");
                transaction.commit();break;
        }
    }

    @Override
    public void respond(int position) {
        portfolio.setText(getResources().getStringArray(R.array.spinner)[position]);
        prepareDataList(position);
    }

    @Override
    public void backpressed(boolean backpressed) {
        if (backpressed){
            finish();
        }
    }
}
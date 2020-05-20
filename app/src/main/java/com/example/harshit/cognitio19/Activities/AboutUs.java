package com.example.harshit.cognitio19.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshit.cognitio19.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AboutUs extends AppCompatActivity implements View.OnClickListener{
    ImageView back,share,rate;
    String appUrl;TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        MainActivity.isWarningShown=false;

        back=findViewById(R.id.back_arrow);
        share=findViewById(R.id.share_icon);
        rate=findViewById(R.id.rate_icon);
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        rate.setOnClickListener(this);
        //mainBody=findViewById(R.id.mainbody);
        title=findViewById(R.id.title);
        Typeface Title_font = Typeface.createFromAsset(getAssets(),  "font/Museo_Slab_500italic.otf");
        title.setTypeface(Title_font);


        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("AppUrl");
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appUrl=dataSnapshot.child("AppUrl").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_arrow:
                finish();break;
            case R.id.share_icon:
                if(appUrl!=null){
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, appUrl);
                    intent.setType("text/plain");
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this,"Network Error...",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rate_icon:
                if(appUrl!=null){
                    Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(appUrl));
                    startActivity(Intent.createChooser(intent, "Open with..."));
                }
                else {
                    Toast.makeText(this,"Network Error...",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}

package com.example.harshit.cognitio19.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.harshit.cognitio19.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class Itinerary extends AppCompatActivity implements View.OnClickListener {

    private ImageView day1, day2, day3;
    public static final String INTENT_PARAM_DAY = "intentParamDay";String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);
        day1 = findViewById(R.id.iv_day1);
        day2 = findViewById(R.id.iv_day2);
        day3 = findViewById(R.id.iv_day3);
        MainActivity.isWarningShown=false;

        findViewById(R.id.cv_day1).setOnClickListener(this);
        findViewById(R.id.cv_day2).setOnClickListener(this);
        findViewById(R.id.cv_day3).setOnClickListener(this);

        makePicassoView(0,day1);
        makePicassoView(1,day2);
        makePicassoView(2,day3);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Itinerary.this, FullScreen.class);
        if (view.getId() == R.id.cv_day1) intent.putExtra(INTENT_PARAM_DAY, 0);
        if (view.getId() == R.id.cv_day2) intent.putExtra(INTENT_PARAM_DAY, 1);
        if (view.getId() == R.id.cv_day3) intent.putExtra(INTENT_PARAM_DAY, 2);
        startActivity(intent);
    }
    private void makePicassoView(final int position, final ImageView iv){

        if(MainActivity.Itinary_Images_Posters.length==3) {
            Picasso.with(this).load(MainActivity.Itinary_Images_Posters[position]).placeholder(R.drawable.placeholder).fit().networkPolicy(NetworkPolicy.OFFLINE).into(iv, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(Itinerary.this).load(MainActivity.Itinary_Images_Posters[position]).placeholder(R.drawable.placeholder).fit().into(iv);
                }
            });
        }
    }

}

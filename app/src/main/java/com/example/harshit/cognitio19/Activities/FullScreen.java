package com.example.harshit.cognitio19.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.harshit.cognitio19.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import static com.example.harshit.cognitio19.Activities.Itinerary.INTENT_PARAM_DAY;

public class FullScreen extends AppCompatActivity implements View.OnClickListener {

    private ImageButton ibPrev, ibNext;ImageView main;
    private int currPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        ibPrev = findViewById(R.id.ib_prev);
        ibNext = findViewById(R.id.ib_next);
        main=findViewById(R.id.iv_itinary);
        currPos = getIntent().getIntExtra(INTENT_PARAM_DAY, 0);
        makePicassoView(currPos,main);

        if (currPos == 0) ibPrev.setVisibility(View.INVISIBLE);
        else if (currPos == 2) ibNext.setVisibility(View.INVISIBLE);

        ibPrev.setOnClickListener(this);
        ibNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        main=findViewById(R.id.iv_itinary);
        if (view == ibPrev){
            if (currPos > 0) {
                currPos--;
                ibNext.setVisibility(View.VISIBLE);
                if (currPos == 0) ibPrev.setVisibility(View.INVISIBLE);
            }
        } else if (view == ibNext) {
            if (currPos < 2) {
                currPos++;
                ibPrev.setVisibility(View.VISIBLE);
                if (currPos == 2) ibNext.setVisibility(View.INVISIBLE);
            }
        }
        makePicassoView(currPos,main);

    }

    private void makePicassoView(final int position, final ImageView iv){

        if(MainActivity.Itinary_Images_Posters.length==3) {
            Picasso.with(this).load(MainActivity.Itinary_Images[position]).placeholder(R.drawable.placeholder).fit().networkPolicy(NetworkPolicy.OFFLINE).into(iv, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(FullScreen.this).load(MainActivity.Itinary_Images[position]).placeholder(R.drawable.placeholder).fit().into(iv);
                }
            });
        }
    }
}

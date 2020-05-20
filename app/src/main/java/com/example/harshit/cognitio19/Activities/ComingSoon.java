package com.example.harshit.cognitio19.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.harshit.cognitio19.R;
import com.example.harshit.cognitio19.Utils.Utilities;

public class ComingSoon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);
        Utilities.changeStatusBarColor(this);
        MainActivity.isWarningShown=false;
    }
}

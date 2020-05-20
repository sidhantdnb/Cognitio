package com.example.harshit.cognitio19.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.harshit.cognitio19.BuildConfig;
import com.example.harshit.cognitio19.R;
import com.example.harshit.cognitio19.Utils.Utilities;
import com.onesignal.OneSignal;

public class SplashScreen extends Activity {

    /** Duration of wait **/
    int SPLASH_DISPLAY_LENGTH = 1500;
    ImageView cognitio,industry,icon;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);
        cognitio=findViewById(R.id.cognitio);
        industry=findViewById(R.id.industry);
        icon=findViewById(R.id.uppericon);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        OneSignal.sendTag("Admin","Admin");
        Utilities.changeStatusBarColor(this);
        Thread mythread=new Thread(){
            @Override
            public void run() {
                try {
                    Animation animation1= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.slide_in_right);
                    Animation animation2= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.slide_in_left);
                    Animation animation3= AnimationUtils.loadAnimation(SplashScreen.this,R.anim.bounce);
                    cognitio.startAnimation(animation2);
                    industry.startAnimation(animation1);
                    icon.startAnimation(animation3);

                    sleep(SPLASH_DISPLAY_LENGTH);
                    Intent i=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }


}

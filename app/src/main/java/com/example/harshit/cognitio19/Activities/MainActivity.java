package com.example.harshit.cognitio19.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshit.cognitio19.Adapters.PosterAdapter;
import com.example.harshit.cognitio19.Modals.EventHeadModal;
import com.example.harshit.cognitio19.Modals.EventsModals;
import com.example.harshit.cognitio19.R;
import com.example.harshit.cognitio19.Utils.DialogPop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.harshit.cognitio19.Utils.Constants;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    ImageView top,bottom,topleft,topright,bottomleft,bottomright,icon, menu,facebook,instagram,whatsapp,mail;
    TextView app_title;
    ProgressDialog progressDialog;
    int csGuest,csItinerary,csSponsors;
    public static String Itinary_Images[];
    public static String Itinary_Images_Posters[];
    public static ArrayList<EventsModals> eventsData;

    public static boolean isWarningShown;

    private static final int BANNER_DELAY_TIME = 4 * 1000;
    private static final int BANNER_TRANSITION_DELAY = 1200;
    private Runnable runnable;
    private Handler handler;
    private boolean firstScroll = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top=findViewById(R.id.faqs);//FAQ_NOTIF
        topleft=findViewById(R.id.core_team);//Core team
        topright=findViewById(R.id.guest);//guest
        bottom=findViewById(R.id.itinerary);//Itinerary
        bottomleft=findViewById(R.id.sponsors);//Sponsors
        bottomright=findViewById(R.id.events);//events
        facebook=findViewById(R.id.facebook);
        instagram=findViewById(R.id.instagram);
        whatsapp=findViewById(R.id.whatsapp);
        mail=findViewById(R.id.mail);
        app_title=findViewById(R.id.app_title);
        isWarningShown=false;

        icon=findViewById(R.id.main_icon);
        menu =findViewById(R.id.corner_menu);

        top.setOnClickListener(this);
        topleft.setOnClickListener(this);
        topright.setOnClickListener(this);
        bottom.setOnClickListener(this);
        bottomright.setOnClickListener(this);
        bottomleft.setOnClickListener(this);
        icon.setOnClickListener(this);
        menu.setOnClickListener(this);
        facebook.setOnClickListener(this);
        instagram.setOnClickListener(this);
        mail.setOnClickListener(this);
        whatsapp.setOnClickListener(this);
        app_title.setOnClickListener(this);


        setPosterImage();
        setEventData();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_REF_IMAGES).child(Constants.FIREBASE_REF_ITINERARY);
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Itinary_Images=new String[(int)dataSnapshot.getChildrenCount()];
                int count=0;
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Itinary_Images[count]=ds.getValue().toString();
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIREBASE_REF_IMAGES).child(Constants.FIREBASE_REF_ITINERARY_POSTERS);
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Itinary_Images_Posters=new String[(int)dataSnapshot.getChildrenCount()];
                int count=0;
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Itinary_Images_Posters[count]=ds.getValue().toString();
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setPosterImage() {
        final ViewPager viewPager = findViewById(R.id.viewpager_poster);
        final CircleIndicator indicator = findViewById(R.id.indicator_slider);

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_REF_POSTER_IMAGE);
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    try{
                        final int img_count=(int)dataSnapshot.getChildrenCount();
                        String data[]=new String[(int)dataSnapshot.getChildrenCount()];
                        int currindex=0;
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            data[currindex]=ds.getValue().toString();
                            currindex++;
                        }
                        viewPager.setAdapter(new PosterAdapter(MainActivity.this, data));
                        indicator.setViewPager(viewPager);

                        handler = new Handler(Looper.getMainLooper());
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                int currItem = viewPager.getCurrentItem();
                                if (currItem == img_count-1){
                                    viewPager.setCurrentItem(0);
                                } else {
                                    viewPager.setCurrentItem(++currItem);
                                }
                            }
                        };
                        handler.postDelayed(runnable, BANNER_DELAY_TIME);
                    }
                    catch (Exception e){}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        viewPager.setOnPageChangeListener(this);
        try{
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(viewPager, new CustomScroller(viewPager.getContext(),BANNER_TRANSITION_DELAY ));
        } catch (Exception e){}
    }

    private class CustomScroller extends Scroller {

        private int mDuration;

        public CustomScroller(Context context, int mDuration) {
            super(context);
            this.mDuration = mDuration;
        }

        public CustomScroller(Context context, Interpolator interpolator, int mDuration) {
            super(context, interpolator);
            this.mDuration = mDuration;
        }

        public CustomScroller(Context context, Interpolator interpolator, boolean flywheel, int mDuration) {
            super(context, interpolator, flywheel);
            this.mDuration = mDuration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (firstScroll){
            firstScroll = false;
        } else {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE){
            handler.postDelayed(runnable, BANNER_DELAY_TIME);
        }
    }

    public void setCommingSoon(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("ComingSoon");
        databaseReference.keepSynced(true);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                csGuest=Integer.parseInt(dataSnapshot.child("Guest1").getValue().toString());
                csItinerary=Integer.parseInt(dataSnapshot.child("Itinerary").getValue().toString());
                csSponsors=Integer.parseInt(dataSnapshot.child("Sponsors").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.app_title:
                startActivity(new Intent(MainActivity.this,AboutUs.class));break;
            case R.id.faqs:
                Intent intent=new Intent(MainActivity.this,FAQ_NOTIF.class);
                intent.putExtra("Button_Pressed","FAQs");
                startActivity(intent);
                break;
            case R.id.core_team:
                startActivity(new Intent(MainActivity.this,CoreTeam.class));
                break;
            case R.id.itinerary:
                if(csItinerary==1){
                    startActivity(new Intent(MainActivity.this, Itinerary.class));
                }
                else {
                    startActivity(new Intent(MainActivity.this,ComingSoon.class));
                }
                break;
            case R.id.guest:
                if(csGuest==1){
                    startActivity(new Intent(MainActivity.this, GuestLecture.class));
                }
                else{
                    startActivity(new Intent(MainActivity.this,ComingSoon.class));
                }break;
            case R.id.sponsors:
                if(csSponsors==1){
                    startActivity(new Intent(MainActivity.this, Sponsors.class));
                }
                else{
                    startActivity(new Intent(MainActivity.this,ComingSoon.class));
                }break;
            case R.id.events:
                startActivity(new Intent(MainActivity.this,Events.class));break;
            case R.id.main_icon:
                startActivity(new Intent(MainActivity.this,AboutUs.class));break;
            case R.id.corner_menu:
                DialogPop surpriseDialog=new DialogPop();
                surpriseDialog.show(getSupportFragmentManager(),"Surprise_Dialog");break;
            case R.id.facebook:
                try {
                    this.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/934857360037086")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/cognitio.me.nitjsr")));
                }break;
            case R.id.mail:
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setType("message/rfc822");
                i.setData(Uri.parse("mailto:cognitio.me@nitjsr.ac.in"));
                i.putExtra(Intent.EXTRA_EMAIL  , "cognitio.me@nitjsr.ac.in");
                i.putExtra(Intent.EXTRA_SUBJECT, "Cognitio'19");
                i.putExtra(Intent.EXTRA_TEXT   , "Hello");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }break;
            case R.id.instagram:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.INSTAGRAM_LINK)));break;
            case R.id.whatsapp:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=91" + "9472903552" + "&text=Hello!!";
                intent.setData(Uri.parse(url));
                startActivity(intent);break;
        }
    }

    @Override
    public void onBackPressed() {
        if (!isWarningShown) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            isWarningShown = true;
        } else {
            finish();
        }
    }
    private void setEventData() {
        eventsData=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        setCommingSoon();
        DatabaseReference database=FirebaseDatabase.getInstance().getReference().child("Events");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                eventsData.clear();
                try {
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        String about=ds.child("About").getValue().toString();
                        String details=ds.child("Details").getValue().toString();
                        ArrayList<EventHeadModal> eventsHead=new ArrayList<>();
                        eventsHead.clear();
                        for (DataSnapshot d:ds.child("EventHead").getChildren()){
                            String image=d.child("photo").getValue().toString();
                            String name=d.child("name").getValue().toString();
                            String phone=d.child("phone").getValue().toString();
                            eventsHead.add(new EventHeadModal(image,name,phone));
                        }
                    eventsData.add(new EventsModals(about,details,eventsHead));
                    }
                }
                catch (Exception e){
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(MainActivity.this,"Problem Loading Data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if (progressDialog.isShowing()) progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Problem Loading Data",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.harshit.cognitio19.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.harshit.cognitio19.Activities.Itinerary;
import com.example.harshit.cognitio19.Activities.MainActivity;
import com.example.harshit.cognitio19.R;
import com.example.harshit.cognitio19.Utils.Utilities;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class PosterAdapter extends PagerAdapter {
    private Context context;
    private String[] imageUrls, clickUrls;
    public PosterAdapter(Context context, String[] imageUrls){
        this.context=context;
        this.imageUrls=imageUrls;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutinflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutinflater.inflate(R.layout.item_view_poster, container, false);
        final ImageView iv = view.findViewById(R.id.iv_poster);

            if(true) {
                Picasso.with(context).load(imageUrls[position]).placeholder(R.drawable.placeholder).fit().networkPolicy(NetworkPolicy.OFFLINE).into(iv, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(imageUrls[position]).placeholder(R.drawable.placeholder).fit().into(iv);
                    }
                });
            }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}

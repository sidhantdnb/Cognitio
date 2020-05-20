package com.example.harshit.cognitio19.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshit.cognitio19.Modals.EventHeadModal;
import com.example.harshit.cognitio19.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventHeadAdapter extends RecyclerView.Adapter<EventHeadAdapter.ViewHolder> {
    Context context;ArrayList<EventHeadModal> eventHead;
    public EventHeadAdapter(Context context,ArrayList<EventHeadModal> eventHead) {
        this.context=context;
        this.eventHead=eventHead;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_sponsers_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Picasso.with(context).load(eventHead.get(position).getImage()).placeholder(R.drawable.placeholder).fit().networkPolicy(NetworkPolicy.OFFLINE).into(holder.iv, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(context).load(eventHead.get(position).getImage()).placeholder(R.drawable.placeholder).fit().into(holder.iv);
            }
        });
        holder.tv.setText(eventHead.get(position).getName());
        try {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+eventHead.get(position).getPhone()));
                    context.startActivity(intent);
                }
            });
        }
        catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return eventHead.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.sponsers_icon);
            tv=itemView.findViewById(R.id.sponsers_desp);
        }
    }
}

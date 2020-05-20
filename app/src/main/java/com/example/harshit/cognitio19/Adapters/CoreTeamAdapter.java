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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harshit.cognitio19.Modals.TeamModal;
import com.example.harshit.cognitio19.R;

import java.util.ArrayList;

public class CoreTeamAdapter extends RecyclerView.Adapter<CoreTeamAdapter.ViewHolder> {

    Context context;
    ArrayList<TeamModal> memberList;
    public CoreTeamAdapter(Context context, ArrayList<TeamModal> memberList){
        this.context=context;
        this.memberList=memberList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_core_team_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final TeamModal member=memberList.get(position);
        holder.mName.setText(member.getMemberName());
        holder.mDesignation.setText(member.getMemberDesignation());
        if(member.getDept()!=null){
            holder.dept.setText(member.getDept());
        }
        else{
            holder.dept.setVisibility(View.GONE);
        }
        if(member.getMemberImage()!=0){
            holder.img.setImageResource(member.getMemberImage());
        }
        else{
            holder.img.setImageResource(R.drawable.placeholder);
        }
        if (member.getWhatsappNo()==null) {
            holder.layout.setVisibility(View.GONE);
            holder.mName.setTextSize(20);
            holder.dept.setTextSize(17);
            holder.mDesignation.setTextSize(17);
        }
        holder.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(member.getWhatsappNo()!=null) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    String url = "https://api.whatsapp.com/send?phone=91" + member.getWhatsappNo() + "&text=Hello!!";
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,whatsapp;
        TextView mName,mDesignation,dept;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            mName=itemView.findViewById(R.id.team_member_name);
            mDesignation=itemView.findViewById(R.id.designation);
            whatsapp=itemView.findViewById(R.id.whatsapp_button);
            layout=itemView.findViewById(R.id.whatsapplayout);
            dept=itemView.findViewById(R.id.dept);
        }
    }
}

package com.example.harshit.cognitio19.EventFolding;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harshit.cognitio19.Activities.MainActivity;
import com.example.harshit.cognitio19.Adapters.EventAdapterView;
import com.example.harshit.cognitio19.Adapters.EventHeadAdapter;
import com.example.harshit.cognitio19.Adapters.SponsorsAdapter;
import com.example.harshit.cognitio19.R;

public class Coordinator extends Fragment {
    TextView heading,body;
    RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_coordinator,container,false);
        heading=view.findViewById(R.id.heading);
        rv=view.findViewById(R.id.rv_eventhead);
        body=view.findViewById(R.id.body);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new EventHeadAdapter(getActivity(),MainActivity.eventsData.get(SubEvent.position).getEventHead()));
        Typeface headingfont= Typeface.createFromAsset(getActivity().getAssets(),"font/Museo_Slab_500italic.otf");
        heading.setTypeface(headingfont);

        return view;
    }
}
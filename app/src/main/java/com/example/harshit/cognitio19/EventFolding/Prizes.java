package com.example.harshit.cognitio19.EventFolding;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harshit.cognitio19.R;

public class Prizes extends Fragment {
    TextView heading,body;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_prizes,container,false);
        heading=view.findViewById(R.id.heading);
        body=view.findViewById(R.id.body);
        Typeface comic = Typeface.createFromAsset(getActivity().getAssets(), "font/Code New Roman.otf");
        Typeface headingfont= Typeface.createFromAsset(getActivity().getAssets(),"font/Museo_Slab_500italic.otf");
        body.setTypeface(comic);
        heading.setTypeface(headingfont);
        return view;
    }
}

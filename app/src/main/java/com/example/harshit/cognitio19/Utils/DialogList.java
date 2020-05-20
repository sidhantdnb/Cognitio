package com.example.harshit.cognitio19.Utils;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.harshit.cognitio19.R;

public class DialogList extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListView listView;
        final Communicator com;
        com= (Communicator) getActivity();
        setCancelable(false);
        setShowsDialog(true);
        View view=inflater.inflate(R.layout.item_dialog_list,container,false);
        listView=view.findViewById(R.id.list_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.respond(position);
                FragmentTransaction transaction=getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("dialogList"));
                transaction.addToBackStack("Dialog");
                transaction.commit();
            }
        });
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            com.backpressed(true);
                            dialog.dismiss();
                        }
                        return true;
            }
        });
        return view;
    }
}
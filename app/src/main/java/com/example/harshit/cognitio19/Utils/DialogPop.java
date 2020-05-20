package com.example.harshit.cognitio19.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.harshit.cognitio19.Activities.ComingSoon;
import com.example.harshit.cognitio19.Activities.CoreTeam;
import com.example.harshit.cognitio19.Activities.FAQ_NOTIF;;
import com.example.harshit.cognitio19.R;

public class DialogPop extends DialogFragment {

    Context context;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        context=getActivity();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        switch (getTag()) {
            case("Surprise_Dialog"):
                builder.setItems(new String[]{"Notifications", "Register"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent2 = new Intent(getActivity(), FAQ_NOTIF.class);
                                intent2.putExtra("Button_Pressed", "Notifications");
                                startActivity(intent2);
                                break;
                            case 1:
                                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                                builder.setItems(new String[]{"UG 1st year", "UG 2nd year", "UG 3rd year/PG","School Student"}, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case 0:
                                                dialog.dismiss();
                                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfAPvTBBFyMMmeKgJeYlNCkopkf_lNfaOo1gcA2RDo5Wx8gYg/viewform")));break;
                                            case 1:
                                                dialog.dismiss();
                                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfbPWqSxkk7Q4OWLZZAO-FfDpgg9DWfW_xE9quDMQk28NaymQ/viewform")));break;
                                            case 2:
                                                dialog.dismiss();
                                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfYA9cxtqOpKyXS5ff3rPvByUoWsSm0gr_WSjMFAGfuc3yV9A/viewform")));break;
                                            case 3:
                                                dialog.dismiss();
                                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSc-EUi8OnH1dNIrGB8P6QeQdlSp4i9zj5fXT4oBXzsHiDm2sw/viewform")));break;
                                        }
                                    }
                                }).create();
                                builder.show();
                                break;
                        }
                    }
                });
                Dialog dialog = builder.create();
                return dialog;
            }
            return null;
    }
}

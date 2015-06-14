package com.sobonus.hackaton.Profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * Created by muhamad.kamal on 7/4/2015.
 */
public class DialogBankNameFragment extends DialogFragment
{
    ListFragmentItemClickListener itemClickListener;

    private String cat[];

    public DialogBankNameFragment(String[] cat) {
        this.cat = cat;

    }

    public interface ListFragmentItemClickListener{

        void onBankNameClick(String cat, int pos);

    }
    public void onAttach(Activity activity){

        super.onAttach(activity);
        try{
            /** This statement ensures that the hosting activity implements ListFragmentItemClickListener */
            itemClickListener = (ListFragmentItemClickListener) activity;
        }catch(Exception e){
            Toast.makeText(activity.getBaseContext(), "Exception", Toast.LENGTH_SHORT).show();
        }

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Select Country")
                .setItems(cat, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        itemClickListener.onBankNameClick(cat[which], which);

                    }
                });

        return builder.create();
    }
}

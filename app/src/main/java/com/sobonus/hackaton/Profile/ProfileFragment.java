package com.sobonus.hackaton.Profile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.sobonus.hackaton.R;
import com.sobonus.hackaton.Util.AlertDialogBox;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileFragment extends Fragment {

    private FragmentManager fm;
    private EditText etBankAccount;
    private EditText etName;
    private EditText etEmail;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    fm = getFragmentManager();
        View rootView= inflater.inflate(R.layout.fragment_profile, container, false);
        LinearLayout rlBankName = (LinearLayout)rootView.findViewById(R.id.rlBankName);
         TextView tvBankName = (TextView) rootView.findViewById(R.id.tvBankName);
        etName = (EditText) rootView.findViewById(R.id.etName);
        etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        etBankAccount = (EditText) rootView.findViewById(R.id.etBankAccount);
        ParseUser user = ParseUser.getCurrentUser();

        etBankAccount.setText(user.getString("bankAccount"));
        etEmail.setText(user.getString("username"));
        etName.setText(user.getString("bankAccNo"));
        tvBankName.setText(user.getString("bankName"));


        LinearLayout rlBankAcc = (LinearLayout)rootView.findViewById(R.id.rlBankAcc);
        rlBankName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] array = {"Maybank","CIMB"};



                DialogBankNameFragment alertdFragment = new DialogBankNameFragment(array);
                alertdFragment.show(fm, "Alert Dialog Fragment");
            }
        });


        return rootView;


    }
}

package com.sobonus.hackaton.Transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.sobonus.hackaton.R;

import java.sql.Date;

/**
 * Created by vicknesh on 6/14/2015.
 */
public class ManualCollect extends ActionBarActivity {

    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_collect);
         intent = getIntent();
        final double amount = Double.parseDouble(intent.getStringExtra("amount"));
        Log.d("Amount", String.valueOf(amount));
        final EditText recepientname = (EditText) findViewById(R.id.receipientname);
        final EditText icpassport = (EditText) findViewById(R.id.icpassport);

        Button manualcollectbutton = (Button) findViewById(R.id.btnSubmit);


        manualcollectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recepient_name = recepientname.getText().toString();
                String ic_passport = icpassport.getText().toString();

                if(recepient_name.length() == 0 || ic_passport.length() == 0)
                {
                    Toast.makeText(v.getContext(), "Please fill the login", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {

                    Date date = new Date(System.currentTimeMillis());
                    ParseObject Transaction = new ParseObject("Transaction");

                    Transaction.put("bankAccName",recepient_name);
                    Transaction.put("Identifier", ic_passport);
                    Transaction.put("amount", amount);
                    Transaction.put("date", date);
                    Transaction.put("fromCurrency", intent.getStringExtra("fromCurrency"));
                    Transaction.put("toCurrency", intent.getStringExtra("toCurrency"));


                    Transaction.saveInBackground();
                    Toast.makeText(getApplicationContext(), "Successfull added", Toast.LENGTH_LONG).show();

                }



            }
        });

    }
}

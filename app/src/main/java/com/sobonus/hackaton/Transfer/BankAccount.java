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

import com.parse.ParseObject;
import com.sobonus.hackaton.R;

import java.sql.Date;

/**
 * Created by vicknesh on 6/14/2015.
 */
public class BankAccount extends ActionBarActivity {
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_account);
        intent = getIntent();
        final double amount = Double.parseDouble(intent.getStringExtra("amount"));
        Log.d("Amount", String.valueOf(amount));
       final EditText banknname = (EditText) findViewById(R.id.bankname);
        final EditText recepientname = (EditText) findViewById(R.id.receipientname);
        final EditText accountnumber = (EditText) findViewById(R.id.accountnumber);

        Button confirmbankaccount = (Button) findViewById(R.id.btnConfirmBankAcc);

        confirmbankaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bank_name = banknname.getText().toString();
                String recepient_name = recepientname.getText().toString();
                String account_number = accountnumber.getText().toString();

                if(recepient_name.length() == 0 || bank_name.length() == 0 || account_number.length() == 0)
                {
                    Toast.makeText(v.getContext(), "Please fill the login", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    Date date = new Date(System.currentTimeMillis());

                    ParseObject Transaction = new ParseObject("Transaction");
                    Transaction.put("bankName", bank_name);
                    Transaction.put("bankAccName",recepient_name);
                    Transaction.put("bankAccNo", account_number);
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

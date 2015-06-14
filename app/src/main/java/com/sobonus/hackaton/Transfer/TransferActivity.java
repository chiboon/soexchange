package com.sobonus.hackaton.Transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sobonus.hackaton.R;
import com.sobonus.hackaton.RateFragment.RateAdapter;


/**
 * Created by vicknesh on 6/13/2015.
 */
public class TransferActivity extends ActionBarActivity {

    private EditText amountyouget;
    private TextView tvCurBank;
    private TextView tvCurSoexchange;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_transfer);
Intent intent = getIntent();
        final int selected = intent.getIntExtra("position", 0);
        final double currencyRate = RateAdapter.items.get(selected).getRate();

        final EditText amountToSend = (EditText)findViewById(R.id.AmounttoSend);
         amountyouget = (EditText)findViewById(R.id.Amountyouget);
        tvCurBank = (TextView)findViewById(R.id.tvCurBank);
        tvCurSoexchange = (TextView)findViewById(R.id.tvCurSoexchange);
        tvCurSoexchange.setText(String.valueOf(currencyRate));
        Log.d("from", RateAdapter.items.get(selected).getFrom());
        getAverageBankConversionRate(RateAdapter.items.get(selected).getFrom(), RateAdapter.items.get(selected).getTo());
        Button moneycollect = (Button) findViewById(R.id.manualcollect);
        Button bankaccount = (Button) findViewById(R.id.bankaccount);

        amountToSend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    double total = Double.parseDouble(String.valueOf(amountToSend.getText())) * currencyRate;
                    amountyouget.setText(String.valueOf(total));
                }catch (Exception e) {
                    amountyouget.setText("");
                }
            }


                @Override
                public void afterTextChanged (Editable editable){

                }
            }

            );


            moneycollect.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){

                Intent moneycollect = new Intent(TransferActivity.this, ManualCollect.class);
                        moneycollect.putExtra("amount", amountToSend.getText().toString());
                    moneycollect.putExtra("fromCurrency", RateAdapter.items.get(selected).getFrom());
                    moneycollect.putExtra("toCurrency", RateAdapter.items.get(selected).getTo());
                startActivity(moneycollect);

            }

            }

            );

            bankaccount.setOnClickListener(new View.OnClickListener()

                                           {
                                               @Override
                                               public void onClick(View v) {
                                                   Intent bankaccount = new Intent(TransferActivity.this, BankAccount.class);
                                                   bankaccount.putExtra("amount", amountToSend.getText().toString());
                                                   bankaccount.putExtra("fromCurrency", RateAdapter.items.get(selected).getFrom());
                                                   bankaccount.putExtra("toCurrency", RateAdapter.items.get(selected).getTo());
                                                   startActivity(bankaccount);

                                               }

                                           }

            );
        }

    private double averageBankConversionRate;
    private double convertWithRate(double amount, double rate) {
        return amount * rate;
    }

    private void getAverageBankConversionRate(String fromCurrency, String toCurrency) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("AverageBankRate");
        query.whereEqualTo("fromCurrency", fromCurrency);
        query.whereEqualTo("toCurrency", toCurrency);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject averageBankRate, ParseException e) {
                if (e == null) {
                    averageBankConversionRate = averageBankRate.getDouble("conversionRate");
                    tvCurBank.setText(String.valueOf(averageBankConversionRate));

                }

            }
        });
    }
    }

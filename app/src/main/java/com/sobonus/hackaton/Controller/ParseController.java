package com.sobonus.hackaton.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.sobonus.hackaton.History.HistoryAdapter;
import com.sobonus.hackaton.History.HistoryDetailsActivity;
import com.sobonus.hackaton.Model.TransactionModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by muhamad.kamal on 13/6/2015.
 */
public class ParseController {

    private TransactionModel tm;
    private List<TransactionModel> transactionList;
    private List<String> fakeList;
    Date date;
    public List<TransactionModel> getTransactionList(final Context context , final ListView listView){
        date = new Date();
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        transactionList = new ArrayList<>();
        fakeList = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Transaction");
        query.include("receiver");
        query.include("sender");
        //query.whereEqualTo("playerName", "Dan Stemkoski");
        query.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> list, ParseException e) {

                if (e == null) {
                    for (ParseObject po : list) {
                        tm = new TransactionModel();

                        tm.setFromCurrency(po.getString("fromCurrency"));
                        tm.setToCurrency(po.getString("toCurrency"));
                        tm.setAmount(po.getDouble("amount"));
                        tm.setConversionRate(po.getDouble("conversionRate"));
                        tm.setDate(String.valueOf(po.getDate("date")));
                        tm.setStatus(po.getInt("status"));
                        tm.setReceiver((ParseUser) po.get("receiver"));
                        tm.setSender(po.getParseUser("sender"));
                        tm.setToCurrency(po.getString("toCurrency"));
                        tm.setBankAccName(po.getString("bankAccName"));
                        tm.setBankName(po.getString("bankName"));
                        tm.setBankAccNo(po.getString("bankAccNo"));

                        String dates = dateFormat.format(po.getDate("date"));
                        String times = timeFormat.format(po.getDate("date"));
                        tm.setDate(dates);
                        tm.setTime(times);


                        transactionList.add(tm);
                        //Log.d("score", "Retrieved " + transactionList.size() + " scores");
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                Log.d("score", "Retrieved " + transactionList.size() + " scores");

                HistoryAdapter historyAdapter = new HistoryAdapter(context, transactionList);
                listView.setAdapter(historyAdapter);

            }//End Done
        });

        return  transactionList;}

}

package com.sobonus.hackaton.Country;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sobonus.hackaton.Controller.Controller;
import com.sobonus.hackaton.Model.CurrencyModel;
import com.sobonus.hackaton.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CountryListActivity extends ActionBarActivity implements OnClickListener {

    public static final String ARG_COUNTRY = "Country";
    Map<String, Integer> mapIndex;
    ListView list;
    private EditText editsearch;
    private EntryAdapter adapter;
    private RelativeLayout rlAlphabet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

       // String[] fruits = getResources().getStringArray(R.array.fruits_array);
          rlAlphabet = (RelativeLayout)findViewById(R.id.rlAlphabet);
        rlAlphabet.setVisibility(View.INVISIBLE);


        list = (ListView) findViewById(R.id.list_fruits);
        new AsyncTask<String, List<CurrencyModel>, ArrayList<CurrencyModel>>() {
            @Override
            protected ArrayList<CurrencyModel> doInBackground(String... strings) {
                Controller c = new Controller();


                return (ArrayList<CurrencyModel>) c.getCurrentcyList(CountryListActivity.this);
            }

            @Override
            protected void onPostExecute(ArrayList<CurrencyModel> itemList) {

                adapter = new EntryAdapter(CountryListActivity.this, itemList);
                list.setAdapter(adapter);
                getIndexList(itemList);
                displayIndex();
            }
        }.execute();


        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

    }

    private void getIndexList(List<CurrencyModel> countryList) {
        mapIndex = new LinkedHashMap<String, Integer>();
        int i=0;
       for(CurrencyModel ci : countryList){
            String countryName = ci.getBaseName();
            String index = countryName.substring(0, 1);

            if (mapIndex.get(index) == null)
                mapIndex.put(index, i);
           Log.d("Index", index + "-" + i);
           i++;
        }

    }

    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>(mapIndex.keySet());
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(
                    R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        TextView selectedIndex = (TextView) v;
                        String seleted = String.valueOf(selectedIndex.getText());
                        //list.setSelection(mapIndex.get(seleted));
                        TextView tvAlphabet = (TextView)findViewById(R.id.tvAlphabet);
                        tvAlphabet.setText(seleted);
                        list.setSelection(mapIndex.get(seleted));
                        rlAlphabet.setVisibility(View.VISIBLE);

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        rlAlphabet.setVisibility(View.INVISIBLE);

                    }
                    return false;
                }
            });
            textView.setTextSize(12);

            indexLayout.addView(textView);
        }
    }

    public void onClick(View view) {
      //  TextView selectedIndex = (TextView) view;
      //  String seleted = String.valueOf(selectedIndex.getText());
       // list.setSelection(mapIndex.get(seleted));

       //     Log.d("Index", String.valueOf(mapIndex.get(seleted)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }




}
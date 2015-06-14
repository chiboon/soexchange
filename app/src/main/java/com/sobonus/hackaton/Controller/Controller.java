package com.sobonus.hackaton.Controller;

import android.content.Context;
import android.util.Log;

import com.sobonus.hackaton.Model.CurrencyModel;
import com.sobonus.hackaton.Model.RateModel;
import com.sobonus.hackaton.Util.AssetJSON;
import com.sobonus.hackaton.Util.Contstant;
import com.sobonus.hackaton.Util.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by muhamad.kamal on 13/6/2015.
 */
public class Controller {
    JSONObject json;
    String jsonString;
    AssetJSON asset = new AssetJSON();
    JSONParser jsonParser = new JSONParser();
    JSONArray data = null;
    List<String>remember;

    public List<CurrencyModel> getCurrentcyList(Context context){
        List<CurrencyModel> currencyList = new ArrayList<>();
        json = asset.LoadAssetFile(context, "currencies.json");
        Iterator<?> keys = json.keys();
        while(keys.hasNext() ) {
            String key = (String)keys.next();
            try {
                Log.d("Currencies", key +"-" +json.getString(key));
                currencyList.add(new CurrencyModel(json.getString(key),key));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


            return currencyList;
    }

public List<RateModel> getRates(String base){
        RateModel rm = new RateModel();
        List<RateModel>listRate = new ArrayList<>();
        remember = new ArrayList<>();
    remember.add("MYR");
    remember.add("SGD");
    remember.add("PHP");
    remember.add("VND");
    remember.add("IDR");

        List<NameValuePair>params = new ArrayList<>();
        params.add(new BasicNameValuePair("base", base));
        params.add(new BasicNameValuePair("apiKey", Contstant.CURRENCY_API_KEY));
        json = jsonParser.makeHttpRequest("http://jsonrates.com/get/",
                "GET", params);
        //Log.d("JsonRate",j);
        try {
            JSONObject rates = json.getJSONObject("rates");
            Iterator<?> keys = rates.keys();

            while(keys.hasNext() ) {
                String key = (String) keys.next();
                try {

//                    Log.d("Currencies", key +"-" +json.getString(key));
                    for(String m : remember) {
                        if (key.contains(m)) {
                            listRate.add(new RateModel(json.getString("base"), key, rates.getDouble(key)));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
//            rm.setFrom(json.getString("from"));
       //     rm.setTo(json.getString("to"));
       //     rm.setRate(Double.parseDouble(json.getString("rate")));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return listRate;
    }


}

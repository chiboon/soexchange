package com.sobonus.hackaton;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sobonus.hackaton.Controller.Controller;
import com.sobonus.hackaton.Model.RateModel;
import com.sobonus.hackaton.Util.DatabaseHelper;
import com.sobonus.hackaton.Util.Util;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*   DatabaseHelper db = Util.executeDB(this);
         db.insertFavourite("USD");
        List<String>basecode = db.getFavouriteList();
        Log.d("baseCode", basecode.get(0).toString());
*/
      /*  new AsyncTask<String,List<RateModel>,List<RateModel>>(){

                @Override
                protected List<RateModel> doInBackground(String... strings) {
                    Controller c = new Controller();
                    List<RateModel> rm = c.getRates("USD");
                    //   Log.d("Rate", String.valueOf(rm.getRate()));
                    return rm;
                }

                @Override
                protected void onPostExecute(List<RateModel> rateModel) {
                    super.onPostExecute(rateModel);
                    Log.d("Rate", String.valueOf(rateModel.get(0).getRate()));
                }
        }.execute();*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.sobonus.hackaton.Home;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sobonus.hackaton.BlankFragment.BlankFragment;
import com.sobonus.hackaton.Controller.Controller;
import com.sobonus.hackaton.Country.CountryListActivity;
import com.sobonus.hackaton.History.HistoryListFragment;
import com.sobonus.hackaton.Model.DrawerModel;
import com.sobonus.hackaton.Model.RateModel;
import com.sobonus.hackaton.Profile.DialogBankNameFragment;
import com.sobonus.hackaton.Profile.ProfileFragment;
import com.sobonus.hackaton.R;
import com.sobonus.hackaton.RateFragment.RateAdapter;
import com.sobonus.hackaton.RateFragment.RateFragment;
import com.sobonus.hackaton.Recepient.RecepientFragment;
import com.sobonus.hackaton.Util.DatabaseHelper;
import com.sobonus.hackaton.Util.Util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends ActionBarActivity implements RateFragment.rateFragmentClick, DialogBankNameFragment.ListFragmentItemClickListener{

    private static final int PICK_BASE = 2;
    private static final int PICK_REMEMBER = 3;

    FragmentManager fm = getSupportFragmentManager();
    private String[] mPlanetTitles;
    public static DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ListView mDrawerList2;
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;
    private String contactID;     // contacts unique ID
    TextView tvCountry;

    private ActionBarDrawerToggle mDrawerToggle;
    public static Toolbar toolbar;
    private String mActivityTitle;
    private String phoneNum;
    private List<NameValuePair> params;
    private String[] textStr;
    private String CountryID;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
   /*     if(UserModel.Username.isEmpty()){
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("memId", Constant.memID));
            new AccountInfoActivity.LogoutAsync(HomeActivity.this,params, AccountInfoActivity.mGoogleApiClient).execute();
        }*/
        db = Util.executeDB(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
        /*    getSupportFragmentManager().beginTransaction()
                        .add(R.id.content_frame, new DashboardFragment())
                    .commit();*/

            /** Create Bundle to pass the paramenter from activity to fragment **/
            Bundle bundle = new Bundle();
            bundle.putInt("page", 0);
            RateFragment fragobj = new RateFragment();
            fragobj.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();

            transaction.add(R.id.content_frame, fragobj, "DashboardFragment").commit();
        }
        final List<DrawerModel> drawerList = new ArrayList<DrawerModel>();

        drawerList.add(new DrawerModel("Exchange","rewards_icn"));
        drawerList.add(new DrawerModel("Profile","redemption_icn"));
        drawerList.add(new DrawerModel("History","airtime_topup_icn"));
        drawerList.add(new DrawerModel("Recipients","transaction_history_icn"));
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawerList);
        HomeDrawerAdapter adapter = new HomeDrawerAdapter(this, drawerList);

        // Set the adapter for the list view
        mDrawerList.setAdapter(adapter);

        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);



                Toast.makeText(getApplicationContext(), drawerList.get(position).getDrawerText(), Toast.LENGTH_SHORT).show();
                Bundle bundle;
                FragmentTransaction transaction;
                switch(position){
                    case 0:
                        bundle = new Bundle();
                        RateFragment fragobj3 = new RateFragment();
                        fragobj3.setArguments(bundle);

                        transaction = getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.content_frame, fragobj3, "RateFragment");
                        transaction.addToBackStack("tag").commit();
                        break;
                    case 1:
                        bundle = new Bundle();
                        ProfileFragment fragobj4 = new ProfileFragment();
                        fragobj4.setArguments(bundle);

                        transaction = getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.content_frame, fragobj4, "ProfileFragment");
                        transaction.addToBackStack("tag").commit();
                        break;
                    case 2:
                        bundle = new Bundle();
                        HistoryListFragment fragobj5 = new HistoryListFragment();
                        fragobj5.setArguments(bundle);

                        transaction = getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.content_frame, fragobj5, "HistoryListFragment");
                        transaction.addToBackStack("tag").commit();
                        break;
                    case 3:
                        bundle = new Bundle();
                        RecepientFragment fragobj6 = new RecepientFragment();
                        fragobj6.setArguments(bundle);

                        transaction = getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.content_frame, fragobj6, "RecepientFragment");
                        transaction.addToBackStack("tag").commit();
                        break;
                 /*   case 0:
                        DashfragmentPage(1);
                        break;
                    case 1:
                        DashfragmentPage(2);
                        break;
                    case 2:
                        DashfragmentPage(0);
                        break;
                    case 3:
                        bundle = new Bundle();
                        TransactionHistoryPagerFragment fragobj4 = new TransactionHistoryPagerFragment();
                        fragobj4.setArguments(bundle);

                        transaction = getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.content_frame, fragobj4, "TransactionHistoryListFragment");
                        transaction.addToBackStack("tag").commit();
                        break;
                   *//* case 4:
                        bundle = new Bundle();
                        FavouriteFragment fragobj5 = new FavouriteFragment();
                        fragobj5.setArguments(bundle);

                        transaction = getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.content_frame, fragobj5, "FavouriteFragment");
                        transaction.addToBackStack("tag").commit();
                        break;
*//*
                    case 4:
                        String appLinkUrl, previewImageUrl;

                    *//*    appLinkUrl = "https://play.google.com/store/apps/details?id=com.fahmiyuseri.esemak";
                        previewImageUrl = "https://lh5.ggpht.com/LHSTPbxPK-dAG25KYIV-Rvwd3CE4YbVDIXhbw-LRkRaBo_LW508NxIf0qea8GJLd4zY=w300";

                        if (AppInviteDialog.canShow()) {
                            AppInviteContent content = new AppInviteContent.Builder()
                                    .setApplinkUrl(appLinkUrl)
                                    .setPreviewImageUrl(previewImageUrl)
                                    .build();
                            AppInviteDialog.show(HomeActivity.this, content);
                        }*//*
                        bundle = new Bundle();
                        InviteFriendsFragment fragobj5 = new InviteFriendsFragment();
                        fragobj5.setArguments(bundle);

                        transaction = getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.content_frame, fragobj5, "InviteFriendsFragment");
                        transaction.addToBackStack("tag").commit();
                        break;
                    case 5:
                        bundle = new Bundle();
                        SettingFragment fragobj9 = new SettingFragment();
                        fragobj9.setArguments(bundle);

                        transaction = getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.content_frame, fragobj9, "SettingFragment");
                        transaction.addToBackStack("tag").commit();
                        break;
                */}
                mDrawerLayout.closeDrawers();

            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setupDrawer();
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //   getSupportActionBar().setHomeButtonEnabled(true);
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
              //  Constant.REQUEST_NEW_ACCESSTOKEN(HomeActivity.this);

            }
        });

    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it xent.
      //  getMenuInflater().inflate(R.menu.menu_home, menu);
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
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
        // Checks whether a hardware keyboard is available
        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
            Toast.makeText(this, "keyboard visible", Toast.LENGTH_SHORT).show();
        } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            Toast.makeText(this, "keyboard hidden", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */

    public void onClick(View v){

        switch (v.getId()) {


        }


    }


    @Override
    public void onRateFragmentClick() {
        Intent intentCountry = new Intent(getApplicationContext(), CountryListActivity.class);
        startActivityForResult(intentCountry, PICK_BASE);
    }

    @Override
    public void onAddClick() {
        Intent intentCountry = new Intent(getApplicationContext(), CountryListActivity.class);
        startActivityForResult(intentCountry, PICK_REMEMBER);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {

            case PICK_BASE :
                if (resultCode == RESULT_OK) {
                    // The user picked a contact.
                    // The Intent's data Uri identifies which contact was selected.

                    // Do something with the contact here (bigger example below)
                    String countryName = data.getStringExtra(CountryListActivity.ARG_COUNTRY);

                    TextView tvCur = (TextView)findViewById(R.id.tvCur);
                    tvCur.setText(countryName);
                    db.updateBaseCode(countryName);
                    final ListView listView = (ListView) findViewById(R.id.listView);

                    new AsyncTask<String,List<RateModel>,List<RateModel>>(){

                        @Override
                        protected List<RateModel> doInBackground(String... strings) {
                            Controller c = new Controller();
                            List<RateModel> rm = c.getRates(db.getBaseCode());
                            //   Log.d("Rate", String.valueOf(rm.getRate()));
                            return rm;
                        }

                        @Override
                        protected void onPostExecute(List<RateModel> rateModel) {
                            super.onPostExecute(rateModel);
                            Log.d("Rate", String.valueOf(rateModel.get(0).getRate()));
                            RateAdapter adapter = new RateAdapter(HomeActivity.this,rateModel,100);
                            listView.setAdapter(adapter);
                        }
                    }.execute();

                }
                break;


        }
    }

    @Override
    public void onBankNameClick(String cat, int pos) {
        TextView tvBankName = (TextView) findViewById(R.id.tvBankName);
        tvBankName.setText(cat);
    }
}

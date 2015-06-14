package com.sobonus.hackaton.History;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sobonus.hackaton.R;

public class HistoryDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        Intent intent = getIntent();
        int selected = intent.getIntExtra("position", 0);

        TextView tvBankName = (TextView) findViewById(R.id.tvBankName);
        TextView tvBankAccNo = (TextView) findViewById(R.id.tvAccNo);
        TextView tvAccName = (TextView) findViewById(R.id.tvAccName);
        TextView tvDate = (TextView) findViewById(R.id.tvDate);
        TextView tvTime = (TextView) findViewById(R.id.tvTime);
        TextView tvFromAmount = (TextView) findViewById(R.id.tvFromAmount);
        TextView tvToCur = (TextView) findViewById(R.id.tvToCur);

/*        TextView tvBankName = (TextView) findViewById(R.id.tvBankName);
        TextView tvBankName = (TextView) findViewById(R.id.tvBankName);
        TextView tvBankName = (TextView) findViewById(R.id.tvBankName);*/


        tvBankName.setText(HistoryAdapter.items.get(selected).getBankName());

        tvBankAccNo.setText(HistoryAdapter.items.get(selected).getBankAccNo());
        if(HistoryAdapter.items.get(selected).getReceiver()!=null) {
            tvAccName.setText(HistoryAdapter.items.get(selected).getReceiver().getUsername());
        }
        else
        {
            tvAccName.setText(HistoryAdapter.items.get(selected).getBankAccName());
        }
        tvDate.setText(HistoryAdapter.items.get(selected).getDate());
        tvTime.setText(HistoryAdapter.items.get(selected).getTime());
        tvFromAmount.setText(String.valueOf(HistoryAdapter.items.get(selected).getAmount()));
        tvToCur.setText(HistoryAdapter.items.get(selected).getToCurrency());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history_details, menu);
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

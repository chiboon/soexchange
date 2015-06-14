package com.sobonus.hackaton.History;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobonus.hackaton.Model.TransactionModel;
import com.sobonus.hackaton.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhamad.kamal on 7/4/2015.
 */
public class HistoryAdapter extends ArrayAdapter<TransactionModel> {
    private LayoutInflater mInflater;
    private Context context;
    private TransactionModel item;
    public static List<TransactionModel> items = new ArrayList<>();
    public HistoryAdapter(Context context, int resource) {
        super(context, resource);
    }

    public HistoryAdapter(Context context, List<TransactionModel> items) {
        super(context, R.layout.row_transaction_list, items);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.items = items;
    }

    public static class ViewHolder {

        TextView tvAmount;
        TextView tvFromCur;
        TextView tvToCur;
        TextView tvToAmount;
        TextView tvReceiver;
        TextView tvDate;





    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            // ROW INFLATION
            row = mInflater.inflate(R.layout.row_transaction_list, parent,
                    false);
            holder = new ViewHolder();
            //holder.ivArrow = (ImageView) row.findViewById(R.id.ivArrow);
            holder.tvAmount = (TextView) row.findViewById(R.id.tvAmount);
            holder.tvToCur= (TextView)row.findViewById(R.id.tvToCur);
            holder.tvFromCur= (TextView)row.findViewById(R.id.tvFromCur);
            holder.tvToAmount= (TextView)row.findViewById(R.id.tvToAmount);
            holder.tvReceiver = (TextView) row.findViewById(R.id.tvReceiver);
          //  holder.tvDate = (TextView) row.findViewById(R.id.tvDate);



            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        item = getItem(position);

        holder.tvAmount.setText(String.valueOf(item.getAmount()));
        holder.tvToAmount.setText(String.valueOf(item.getAmount()*item.getConversionRate()));
        holder.tvToCur.setText(String.valueOf(item.getToCurrency()));
        holder.tvFromCur.setText(String.valueOf(item.getFromCurrency()));
if(item.getReceiver()!=null) {
            holder.tvReceiver.setText(item.getReceiver().getUsername());
        }
        else
    holder.tvReceiver.setText(item.getBankAccName());

        //holder.tvDate.setText(item.getDate());





        //Image

         row.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent = new Intent(context, HistoryDetailsActivity.class);
                 // intent.putExtra("TransactionModel", (Serializable) item);
                 intent.putExtra("toAmount",item.getAmount()*item.getConversionRate());
                 intent.putExtra("toCur",item.getToCurrency());
                 if(item.getReceiver()!=null) {
                     intent.putExtra("bankName", item.getReceiver().getUsername());
                 }
                 else
                         {
                         intent.putExtra("bankName", item.getBankAccName());
                     }
                 intent.putExtra("bankAccountNo",item.getBankAccNo());
                 intent.putExtra("date",item.getDate());
                 intent.putExtra("time",item.getTime());
                 intent.putExtra("position",position);
                 Log.d("position", String.valueOf(items.get(position)));

                 context.startActivity(intent);

             }
         });
        return row;
    }


}

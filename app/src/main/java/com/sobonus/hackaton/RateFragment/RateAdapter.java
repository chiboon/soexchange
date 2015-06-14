package com.sobonus.hackaton.RateFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobonus.hackaton.Model.RateModel;
import com.sobonus.hackaton.R;
import com.sobonus.hackaton.Transfer.TransferActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhamad.kamal on 7/4/2015.
 */
public class RateAdapter extends ArrayAdapter<RateModel> {
    private LayoutInflater mInflater;
    private Context context;
    private RateModel item;
    private double amount;
    public static List<RateModel> items = new ArrayList<>();

    public RateAdapter(Context context, int resource) {
        super(context, resource);
    }

    public RateAdapter(Context context, List<RateModel> items, double amount) {
        super(context, R.layout.row_rate_list, items);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.amount = amount;
        this.items = items;
    }

    public static class ViewHolder {

        TextView tvCur;
        TextView tvRate;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            // ROW INFLATION
            row = mInflater.inflate(R.layout.row_rate_list, parent,
                    false);
            holder = new ViewHolder();
            //holder.ivArrow = (ImageView) row.findViewById(R.id.ivArrow);
            holder.tvCur = (TextView) row.findViewById(R.id.tvCur);
            holder.tvRate= (TextView)row.findViewById(R.id.tvRate);




            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        item = getItem(position);
        String rate = String.valueOf(item.getRate()*amount);
        holder.tvCur.setText(item.getTo());
        holder.tvRate.setText(rate);



        //Image

         row.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
          /*       int orderID = item.getOrderID();

                 Intent intent = new Intent(context, PaymentSuccessActivity.class);
                 intent.putExtra("OrderID", orderID);
                 intent.putExtra("parentClass", "transaction");

                 context.startActivity(intent);*/
/*
                 Intent intent = new Intent(context, PaymentSuccessActivity.class);
                 intent.putExtra("OrderID", orderID);
                 intent.putExtra("parentClass", "transaction");

                 context.startActivity(intent)*/

                 Intent intent = new Intent(context, TransferActivity.class);
                // intent.putExtra("OrderID", orderID);
                 intent.putExtra("position", position);

                 context.startActivity(intent);

             }
         });
        return row;
    }


}

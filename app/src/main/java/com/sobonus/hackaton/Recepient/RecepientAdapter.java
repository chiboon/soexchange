package com.sobonus.hackaton.Recepient;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sobonus.hackaton.History.HistoryDetailsActivity;
import com.sobonus.hackaton.Model.RecipientModel;
import com.sobonus.hackaton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhamad.kamal on 14/6/2015.
 */
public class RecepientAdapter extends ArrayAdapter<RecipientModel> {

    private LayoutInflater mInflater;
    private Context context;
private RecipientModel item;
public static List<RecipientModel> items = new ArrayList<>();
public RecepientAdapter(Context context, int resource) {
        super(context, resource);
        }

public RecepientAdapter(Context context, List<RecipientModel> items) {
        super(context, R.layout.row_transaction_list, items);
        mInflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.items = items;
        }

public static class ViewHolder {

    TextView tvRecipient;
    TextView tvLocation;






}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            // ROW INFLATION
            row = mInflater.inflate(R.layout.row_recepient_list, parent,
                    false);
            holder = new ViewHolder();
            //holder.ivArrow = (ImageView) row.findViewById(R.id.ivArrow);
            holder.tvRecipient = (TextView) row.findViewById(R.id.tvRecipient);
            holder.tvLocation = (TextView) row.findViewById(R.id.tvLocation);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        item = getItem(position);

        holder.tvRecipient.setText(String.valueOf(item.getName()));
        holder.tvLocation.setText("Location :"+String.valueOf(item.getLocation()));


        //Image
    return row;}
    }

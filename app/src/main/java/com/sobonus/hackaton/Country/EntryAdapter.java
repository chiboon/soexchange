package com.sobonus.hackaton.Country;

/**
 * Created by muhamad.kamal on 2/6/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobonus.hackaton.Model.CurrencyModel;
import com.sobonus.hackaton.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EntryAdapter extends ArrayAdapter<CurrencyModel> {

    private Context context;
    private ArrayList<CurrencyModel> items;
    private LayoutInflater vi;
    private ArrayList<CurrencyModel> arraylist;

    public EntryAdapter(Context context,ArrayList<CurrencyModel> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        this.arraylist = new ArrayList<CurrencyModel>();
        this.arraylist.addAll(items);
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    public class ViewHolder {
        TextView title;
        TextView subtitle;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final CurrencyModel i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                CurrencyModel si = (CurrencyModel)i;
                v = vi.inflate(R.layout.list_item_section, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
                sectionView.setText(si.getBaseName());
            }else{
                CurrencyModel ei = (CurrencyModel)i;
                v = vi.inflate(R.layout.list_item_entry, null);
                final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
                final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
                //final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
                ImageView imgv = (ImageView)v.findViewById(R.id.list_item_entry_drawable);

                title.setText(ei.getBaseName());
                subtitle.setText(ei.getBaseCode());
           /*     String lowerCaseCountryName[] = ei.getCountryName().split(" - ");
                imgv.setImageBitmap(getPicture(lowerCaseCountryName[0].toLowerCase()));
                if (title != null)
                    title.setText(lowerCaseCountryName[0]);
                if(subtitle != null)
                    subtitle.setText(lowerCaseCountryName[1]);*/


            }
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!i.isSection()) {
                    String baseCode = i.getBaseCode();

                    Intent intent = ((Activity) context).getIntent();
                    intent.putExtra(CountryListActivity.ARG_COUNTRY, baseCode);

                    ((Activity) context).setResult(((Activity) context).RESULT_OK,
                            intent);
                    ((Activity) context).finish();

                    Log.d("baseCode", baseCode);
                }
            }
        });
        return v;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(arraylist);
        }
        else
        {
            for (CurrencyModel ei : arraylist)
            {
                if (ei.getBaseName().toLowerCase(Locale.getDefault()).startsWith(charText))
                {
                    items.add(ei);
                }
            }
        }
        notifyDataSetChanged();
    }

public Bitmap getPicture(String countryname){
    InputStream is = null;
    try {
        is = context.getResources().getAssets().open("flag/"+countryname+".png");
    } catch (IOException e) {

    }

    Bitmap image = BitmapFactory.decodeStream(is);
return  image;}
}

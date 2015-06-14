package com.sobonus.hackaton.Home;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobonus.hackaton.Model.DrawerModel;
import com.sobonus.hackaton.R;

import java.util.List;

/**
 * Created by muhamad.kamal on 7/4/2015.
 */
public class HomeDrawerAdapter extends ArrayAdapter<DrawerModel> {
    private LayoutInflater mInflater;
    private Context context;
    ViewHolder holder;
    ViewHolder2 holder2;

    @Override
    public boolean isEnabled(int position) {
      /*  if(position==2){
            return false;
        }
        else*/
        return true;
    }
    public HomeDrawerAdapter(Context context, int resource) {
        super(context, resource);
    }

    public HomeDrawerAdapter(Context context, List<DrawerModel> items) {
        super(context, R.layout.drawer_list_item, items);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    public static class ViewHolder {

        TextView tvDrawerText;
        ImageView imgDrawer;


    }
    public static class ViewHolder2{
   //    public static LikeView likeView;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;


/* if(position==6) {

   if (row == null) {

        // ROW INFLATION
        row = mInflater.inflate(R.layout.likefb, parent,
                false);
        holder2 = new ViewHolder2();
        //holder.ivArrow = (ImageView) row.findViewById(R.id.ivArrow);
        holder2.likeView = (LikeView) row.findViewById(R.id.like_view);


        row.setTag(holder2);
    }else{
        holder2 = (ViewHolder2) row.getTag();

    }}     else{
*/
    if (row == null) {

        // ROW INFLATION
        row = mInflater.inflate(R.layout.drawer_list_item, parent,
                false);
        holder = new ViewHolder();
        //holder.ivArrow = (ImageView) row.findViewById(R.id.ivArrow);
        holder.tvDrawerText = (TextView) row.findViewById(R.id.tvDrawerText);
        holder.imgDrawer = (ImageView) row.findViewById(R.id.imgDrawer);


        row.setTag(holder);
    } else {
        holder = (ViewHolder) row.getTag();
    }

/*}*/

    DrawerModel item = getItem(position);

    holder.tvDrawerText.setText(String.valueOf(item.getDrawerText()));
    //Image
    int res = context.getResources().getIdentifier(item.getDrawerImg(), "drawable", context.getPackageName());
    holder.imgDrawer.setImageResource(res);

        /*likeView.setObjectIdAndType(
                "https://www.facebook.com/FacebookDevelopers",
                LikeView.ObjectType.PAGE);*/

        return row;
    }

    public void setHighlighted(int pos){

        holder.tvDrawerText.setTypeface(Typeface.DEFAULT_BOLD);
    }


}

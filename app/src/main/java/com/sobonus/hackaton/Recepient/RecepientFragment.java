package com.sobonus.hackaton.Recepient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sobonus.hackaton.Controller.ParseController;
import com.sobonus.hackaton.Model.RecipientModel;
import com.sobonus.hackaton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhamad.kamal on 14/6/2015.
 */
public class RecepientFragment extends Fragment {


    private ListView listview;

    public RecepientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recepient_list, container, false);
         List<RecipientModel> list = new ArrayList<RecipientModel>();
        list.add(new RecipientModel("Vick", "Malaysia"));
        listview = (ListView) rootView.findViewById(R.id.listView);
        RecepientAdapter adapter = new RecepientAdapter(getActivity(),list);
        listview.setAdapter(adapter);
        //  Log.d("History", String.valueOf(pc.getTransactionList().size()));
     //   ParseController pc = new ParseController();
     //   pc.getTransactionList(getActivity(), listview);

        return rootView;
    }
}

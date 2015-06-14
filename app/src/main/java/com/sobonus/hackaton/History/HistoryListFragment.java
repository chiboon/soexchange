package com.sobonus.hackaton.History;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sobonus.hackaton.Controller.ParseController;
import com.sobonus.hackaton.Model.TransactionModel;
import com.sobonus.hackaton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryListFragment extends Fragment {


    private ListView listview;

    public HistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history_list, container, false);
       // List<TransactionModel>list = pc.getTransactionList();
         listview = (ListView) rootView.findViewById(R.id.listView);
      //  Log.d("History", String.valueOf(pc.getTransactionList().size()));
        ParseController pc = new ParseController();
        pc.getTransactionList(getActivity(), listview);

        return rootView;
    }


}

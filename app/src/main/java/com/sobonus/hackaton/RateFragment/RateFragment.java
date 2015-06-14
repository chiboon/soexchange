package com.sobonus.hackaton.RateFragment;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sobonus.hackaton.Controller.Controller;
import com.sobonus.hackaton.Model.RateModel;
import com.sobonus.hackaton.R;
import com.sobonus.hackaton.Util.DatabaseHelper;
import com.sobonus.hackaton.Util.Util;

import java.util.List;


public class RateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private rateFragmentClick mListener;
    private ListView listView;
    private DatabaseHelper db;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RateFragment newInstance(String param1, String param2) {
        RateFragment fragment = new RateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         db = Util.executeDB(getActivity());
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rate, container, false);
         listView = (ListView) rootView.findViewById(R.id.listView);
        LinearLayout lBase = (LinearLayout)rootView.findViewById(R.id.lBase);
        lBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRateFragmentClick();

            }
        });
        TextView tvCur = (TextView)rootView.findViewById(R.id.tvCur);
        tvCur.setText(db.getBaseCode());
        Button btnAdd = (Button) rootView.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mListener.onAddClick();

            }
        });
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
                RateAdapter adapter = new RateAdapter(getActivity(),rateModel,100);
                listView.setAdapter(adapter);
            }
        }.execute();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onRateFragmentClick();
        }
    }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {
                mListener = (rateFragmentClick) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface rateFragmentClick {
        // TODO: Update argument type and name
        public void onRateFragmentClick();
        public void onAddClick();

    }

}

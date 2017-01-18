package edu.temple.bitcoin;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class price_index_frag extends Fragment {

    private static final String node = "USD";
    private static final String btukey = "15m";
    private static final String buykey = "buy";
    private static final String sellkey = "sell";
    static float exch = 746;

    private Handler mHandler;
    private Runnable mRunnable;

    public price_index_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (mHandler != null) {
            mHandler.postDelayed(mRunnable, 2000);
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_price_index_frag, container, false);
        mRunnable = new Runnable() {
            @Override
            public void run() {

                getActivity().setTitle(R.string.price_heading);

                JSONObject rates = null;
                try {
                    rates = MainActivity.fetchservice.getrates();
                } catch (ExecutionException e) {
                    Log.d("Execution exception","price fragment to fetchdata to asynctask");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    Log.d("Interrupted exception","price fragment to fetchdata to asynctask");
                    e.printStackTrace();
                }

                if (rates.length() == 0) Log.d("Empty Jsonobject", "price fragment");

                JSONObject usdrates;

                String btutxt = null;
                String buytxt = null;
                String selltxt = null;

                try {
                    usdrates = rates.getJSONObject(node);
                    btutxt = usdrates.getString(btukey);
                    buytxt = usdrates.getString(buykey);
                    selltxt = usdrates.getString(sellkey);
                } catch (JSONException e) {
                    Log.d("JSON data error", "price fragment");
                }

                TextView btu = (TextView) view.findViewById(R.id.btu);
                TextView utb = (TextView) view.findViewById(R.id.utb);
                TextView buy = (TextView) view.findViewById(R.id.buy);
                TextView sell = (TextView) view.findViewById(R.id.sell);

                btu.setText(btutxt);
                buy.setText(buytxt);
                sell.setText(selltxt);

                float utbval = Float.valueOf(btutxt);
                exch = utbval;
                utbval = 1 / utbval;
                utb.setText(String.valueOf(utbval));


            }
        };
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHandler = new Handler();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

}

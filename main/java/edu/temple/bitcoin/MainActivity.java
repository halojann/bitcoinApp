package edu.temple.bitcoin;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;


public class MainActivity extends Activity {

    public static boolean dual;
    public static fetchData fetchservice;
    boolean mbound = false;
    public static int ori = 0;
    public static int ini = 0;

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, fetchData.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mbound) {
            unbindService(mConnection);
            mbound = false;
        }
    }

    public ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("Check", "Service Connected");
            fetchData.LocalBinder binder = (fetchData.LocalBinder) service;
            fetchservice = binder.getService();
            mbound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mbound = false;
            Log.d("Check", "Service Disconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration configuration = getResources().getConfiguration();
        dual = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE;

        FragmentManager fragman = getFragmentManager();
        FragmentTransaction fragtran = fragman.beginTransaction();

        MenuFragment menu = new MenuFragment();

        //if dual show bitcoin price as detail frag
        if (dual) {
            fragtran.add(R.id.Master, menu);
            if (ori == 0) {
                price_index_frag pi = new price_index_frag();
                fragtran.add(R.id.Detail, pi);
                fragtran.commit();
                ini = 1;
            }
        } else {

            fragtran.add(R.id.Master, menu);
            fragtran.commit();
        }
        fragman.executePendingTransactions();

    }
}

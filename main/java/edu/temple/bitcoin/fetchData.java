package edu.temple.bitcoin;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class fetchData extends Service {

    private final IBinder mBinder = new LocalBinder();

    static String result = "";

    public class LocalBinder extends Binder {

        fetchData getService() {
            return fetchData.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //method for retreiving bitcoin rates
    public JSONObject getrates() throws ExecutionException, InterruptedException {
        result = "";
        JSONObject jsonObject = new JSONObject();
        MyTask task = new MyTask();

        result = result.concat(task.execute("getrates").get());

        try {
            jsonObject = new JSONObject(result);

        } catch (Exception e) {
            Log.d("json error", "fetchdata - rates");
        }

        return jsonObject;
    }

    //get block details
    public JSONObject getblock(String block_id) throws ExecutionException, InterruptedException {
        result = "";
        JSONObject jsonObject = new JSONObject();
        MyTask task = new MyTask();

        result = result.concat(task.execute("getblock", block_id).get());

        try {
            jsonObject = new JSONObject(result);

        } catch (Exception e) {
            Log.d("json error", "fetchdata - block");
        }

        return jsonObject;
    }

    //method for retreiving address details
    public JSONObject getaddress(String address) throws ExecutionException, InterruptedException {
        result = "";
        JSONObject jsonObject = new JSONObject();
        MyTask task = new MyTask();

        result = result.concat(task.execute("getaddress", address).get());

        try {
            jsonObject = new JSONObject(result);

        } catch (Exception e) {
            Log.d("json error", "fetchdata - address");
        }

        return jsonObject;
    }

    public static class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String task = params[0];

            if (task.equals("getrates")) {

                try {
                    Log.d("task begin", "getrates");
                    URL url = new URL("https://blockchain.info/ticker");
                    HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
                    c.setRequestMethod("GET");
                    c.connect();
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        result = result.concat(line);
                    }
                    br.close();
                } catch (Exception e) {
                    Log.d("task error", "getrates");
                }
                return result;
            }
            if (task.equals("getblock")) {

                String block_id = params[1];
                String link = "https://btc.blockr.io/api/v1/block/info/";

                Log.d("Block id", link.concat(block_id));

                try {
                    URL url = new URL(link.concat(block_id));
                    HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
                    c.setRequestMethod("GET");
                    c.connect();
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        result = result.concat(line);
                    }
                    br.close();
                } catch (Exception e) {
                    Log.d("task error", "getblock");
                }

                return result;
            }
            if (task.equals("getaddress")) {

                String address = params[1];
                String link = "https://btc.blockr.io/api/v1/address/info/";
                Log.d("Address id", link.concat(address));

                try {
                    URL url = new URL(link.concat(address));
                    HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
                    c.setRequestMethod("GET");
                    c.connect();
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        result = result.concat(line);
                    }
                    br.close();
                } catch (Exception e) {
                    Log.d("task error", "getaddress");
                }

                return result;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}


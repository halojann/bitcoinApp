package edu.temple.bitcoin;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class block_frag extends Fragment {

    String blockid;
    String next_block;
    String prev_block;
    EditText editText;

    public block_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_block_frag, container, false);
        getActivity().setTitle(R.string.block_heading);

        Button button = (Button) view.findViewById(R.id.blockbutton);
        Button next = (Button) view.findViewById(R.id.nextblockbutton);
        Button prev = (Button) view.findViewById(R.id.prevblockbutton);
        final TextView blockindexvalue = (TextView) view.findViewById(R.id.blockindexvalue);
        final TextView versionvalue = (TextView) view.findViewById(R.id.vervalue);
        final TextView hashvalue = (TextView) view.findViewById(R.id.hashvalue);
        final TextView timevalue = (TextView) view.findViewById(R.id.receivedtimevalue);
        final TextView nextblockvalue = (TextView) view.findViewById(R.id.nextblockvalue);
        final TextView feevalue = (TextView) view.findViewById(R.id.feevalue);
        final TextView prevblockvalue = (TextView) view.findViewById(R.id.prevblockvalue);
        final TextView sizevalue = (TextView) view.findViewById(R.id.sizevalue);
        editText = (EditText) view.findViewById(R.id.edittextblock);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject block = null;
                blockid = editText.getText().toString();

                try {
                    block = MainActivity.fetchservice.getblock(blockid);

                } catch (ExecutionException e) {
                    Log.d("Execution exception", "block fragment to fetchdata to asynctask");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    Log.d("Interrupted exception", "block fragment to fetchdata to asynctask");
                    e.printStackTrace();
                }

                if (block.length() == 0) Log.d("Empty jsonobject", "block fragment");

                JSONObject data;
                String blockindex = null;
                String version = null;
                String hash = null;
                String time = null;
                String nextblock = null;
                String fee = null;
                String prevblock = null;
                String size = null;

                try {
                    data = block.getJSONObject("data");
                    blockindex = data.getString("confirmations");
                    version = data.getString("version");
                    hash = data.getString("hash");
                    time = data.getString("time_utc");
                    nextblock = data.getString("next_block_hash");
                    fee = data.getString("fee");
                    prevblock = data.getString("prev_block_hash");
                    size = data.getString("size");

                } catch (JSONException e) {
                    Log.d("JSON data error", "block fragment");
                }

                blockindexvalue.setText(blockindex);
                versionvalue.setText(version);
                hashvalue.setText(hash);
                timevalue.setText(time);
                nextblockvalue.setText(nextblock);
                feevalue.setText(fee);
                prevblockvalue.setText(prevblock);
                sizevalue.setText(size);

                next_block = nextblock;
                prev_block = prevblock;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject block = null;

                try {
                    block = MainActivity.fetchservice.getblock(next_block);
                } catch (ExecutionException e) {
                    Log.d("Execution exception", "block fragment to fetchdata to asynctask");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    Log.d("Interrupted exception", "block fragment to fetchdata to asynctask");
                    e.printStackTrace();
                }

                if (block.length() == 0) Log.d("Empty jsonobject", "block fragment");

                JSONObject data;
                String blockindex = null;
                String version = null;
                String hash = null;
                String time = null;
                String nextblock = null;
                String fee = null;
                String prevblock = null;
                String size = null;

                try {
                    data = block.getJSONObject("data");
                    blockindex = data.getString("confirmations");
                    version = data.getString("version");
                    hash = data.getString("hash");
                    time = data.getString("time_utc");
                    nextblock = data.getString("next_block_hash");
                    fee = data.getString("fee");
                    prevblock = data.getString("prev_block_hash");
                    size = data.getString("size");

                } catch (JSONException e) {
                    Log.d("JSON data error", "block fragment");
                }

                blockindexvalue.setText(blockindex);
                versionvalue.setText(version);
                hashvalue.setText(hash);
                timevalue.setText(time);
                nextblockvalue.setText(nextblock);
                feevalue.setText(fee);
                prevblockvalue.setText(prevblock);
                sizevalue.setText(size);

                next_block = nextblock;
                prev_block = prevblock;
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject block = null;

                try {
                    block = MainActivity.fetchservice.getblock(prev_block);
                } catch (ExecutionException e) {
                    Log.d("Execution exception", "block fragment to fetchdata to asynctask");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    Log.d("Interrupted exception", "block fragment to fetchdata to asynctask");
                    e.printStackTrace();
                }

                if (block.length() == 0) Log.d("Empty jsonobject", "block fragment");

                JSONObject data;
                String blockindex = null;
                String version = null;
                String hash = null;
                String time = null;
                String nextblock = null;
                String fee = null;
                String prevblock = null;
                String size = null;

                try {
                    data = block.getJSONObject("data");
                    blockindex = data.getString("confirmations");
                    version = data.getString("version");
                    hash = data.getString("hash");
                    time = data.getString("time_utc");
                    nextblock = data.getString("next_block_hash");
                    fee = data.getString("fee");
                    prevblock = data.getString("prev_block_hash");
                    size = data.getString("size");

                } catch (JSONException e) {
                    Log.d("JSON data error", "block fragment");
                }

                blockindexvalue.setText(blockindex);
                versionvalue.setText(version);
                hashvalue.setText(hash);
                timevalue.setText(time);
                nextblockvalue.setText(nextblock);
                feevalue.setText(fee);
                prevblockvalue.setText(prevblock);
                sizevalue.setText(size);

                next_block = nextblock;
                prev_block = prevblock;
            }
        });

        return view;
    }

}

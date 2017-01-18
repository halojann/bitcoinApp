package edu.temple.bitcoin;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class Address_frag extends Fragment {

    private String[] add = new String[]{"", "", ""};

    public Address_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_frag, container, false);
        getActivity().setTitle(R.string.address_heading);
        Button button = (Button) view.findViewById(R.id.addressbutton);
        final TextView balus = (TextView) view.findViewById(R.id.balusd);
        final TextView balbtc = (TextView) view.findViewById(R.id.balbtc);

        SharedPreferences fav = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        add[0] = fav.getString("f0", "");
        add[1] = fav.getString("f1", "");
        add[2] = fav.getString("f2", "");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_selectable_list_item, add);
        final AutoCompleteTextView editText = (AutoCompleteTextView) view.findViewById(R.id.address_edit_text);
        editText.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = editText.getText().toString();
                SharedPreferences fav = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor = fav.edit();
                String e = "f";
                int num = fav.getInt("i", 0);
                num = num % 3;
                editor.putString(e + num, val);
                num++;
                editor.putInt("i", num);
                editor.apply();
                JSONObject address = null;

                try {
                    address = MainActivity.fetchservice.getaddress(val);

                } catch (ExecutionException ex) {
                    Log.d("Execution exception", "address fragment to fetchdata to asynctask");
                    ex.printStackTrace();
                } catch (InterruptedException exi) {
                    Log.d("Interrupted exception", "address fragment to fetchdata to asynctask");
                    exi.printStackTrace();
                }

                if (address.length() == 0) Log.d("Empty jsonobject", "address fragment");

                JSONObject data;
                float balance = 0;

                try {
                    data = address.getJSONObject("data");
                    balance = Float.valueOf(data.getString("balance"));

                } catch (JSONException ex) {
                    Log.d("JSON data error", "address fragment");
                }

                balbtc.setText(String.valueOf(balance));
                balus.setText(String.valueOf(balance * price_index_frag.exch));
            }
        });
        return view;
    }

}

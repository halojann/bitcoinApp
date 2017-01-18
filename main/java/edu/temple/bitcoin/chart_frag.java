package edu.temple.bitcoin;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class chart_frag extends Fragment {

    public chart_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart_frag, container, false);
        getActivity().setTitle(R.string.chart_heading);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        final WebView webView = (WebView) view.findViewById(R.id.chart);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.chartspinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        webView.loadUrl("https://chart.yahoo.com/z?s=BTCUSD=X&t=1d");
                        break;

                    case 1:
                        webView.loadUrl("https://chart.yahoo.com/z?s=BTCUSD=X&t=5d");
                        break;

                    case 2:
                        webView.loadUrl("https://chart.yahoo.com/z?s=BTCUSD=X&t=30d");
                        break;

                    case 3:
                        webView.loadUrl("https://chart.yahoo.com/z?s=BTCUSD=X&t=180d");
                        break;

                    case 4:
                        webView.loadUrl("https://chart.yahoo.com/z?s=BTCUSD=X&t=365d");
                        break;

                    case 5:
                        webView.loadUrl("https://chart.yahoo.com/z?s=BTCUSD=X&t=730d");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

}

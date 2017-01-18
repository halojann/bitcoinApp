package edu.temple.bitcoin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        //Menu initialization
        ListView listView = (ListView) view.findViewById(R.id.menu);
        String[] items = getResources().getStringArray(R.array.mainmenu);
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);


        //Menu controls
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.ori = 1;
                Log.d("Main menu click", String.valueOf(position));

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                switch (position) {

                    case 0://Bitcoin Price Index
                        if (MainActivity.ini != 1) {
                            MainActivity.ini = 1;
                            price_index_frag pi = new price_index_frag();
                            if (MainActivity.dual) {
                                ft.replace(R.id.Detail, pi);
                            } else {
                                ft.replace(R.id.Master, pi);
                            }
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        break;
                    case 1://Bitcoin Date Price Chart
                        if (MainActivity.ini != 2) {
                            MainActivity.ini = 2;
                            chart_frag cf = new chart_frag();
                            if (MainActivity.dual) {
                                ft.replace(R.id.Detail, cf);
                            } else {
                                ft.replace(R.id.Master, cf);
                            }
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        break;
                    case 2://Block information
                        if (MainActivity.ini != 3) {
                            MainActivity.ini = 3;
                            block_frag bf = new block_frag();
                            if (MainActivity.dual) {
                                ft.replace(R.id.Detail, bf);
                            } else {
                                ft.replace(R.id.Master, bf);
                            }
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        break;
                    case 3://Address information
                        if (MainActivity.ini != 4) {
                            MainActivity.ini = 4;
                            Address_frag af = new Address_frag();
                            if (MainActivity.dual) {
                                ft.replace(R.id.Detail, af);
                            } else {
                                ft.replace(R.id.Master, af);
                            }
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        break;

                }

                fm.executePendingTransactions();
            }
        });

        return view;
    }

}

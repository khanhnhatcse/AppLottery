package com.swift.nhat.applottery;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import com.swift.nhat.applottery.Activities.DetailActivity;
import com.swift.nhat.applottery.Activities.LoadingFragment;
import com.swift.nhat.applottery.Adapters.ExpandableListAdapter;
import com.swift.nhat.applottery.Presenter.PresenterImpMain;
import com.swift.nhat.applottery.Presenter.PresenterMain;
import com.swift.nhat.applottery.Rest.ClientApi;
import com.swift.nhat.applottery.Rest.LotteryApi;
import com.swift.nhat.applottery.Utils.DataExtral;
import com.swift.nhat.applottery.Utils.Utils;
import com.swift.nhat.applottery.View.ViewMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ViewMain {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    PresenterImpMain presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        if(isInternetAvailable()) {
            getData();
            Log.d("Test", "Connected  !");
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Lỗi");
            builder.setMessage("Hiện tại không có mạng ! Chuyển sang chế độ offline");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.create().show();
        }
        presenter = new PresenterMain(this);

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild) ;
        expListView.setAdapter(listAdapter);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                presenter.setKey((String)listAdapter.getGroup(groupPosition), (String)listAdapter.getChild(groupPosition, childPosition));
                return true;
            }
        });

    }


    private void loading(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        LoadingFragment f = new LoadingFragment();
        fragmentTransaction.add(R.id.layout_view, f, "loading");
        fragmentTransaction.commit();
        expListView.setVisibility(View.INVISIBLE);
    }

    private void getData(){
        LotteryApi  api = ClientApi.getClient().create(LotteryApi.class);
        Call<Map<String, Map<String, Map<String, ArrayList<String>>>>> call = api.getData();
        loading();
        call.enqueue(new Callback<Map<String, Map<String, Map<String, ArrayList<String>>>>>() {
            @Override
            public void onResponse(Call<Map<String, Map<String, Map<String, ArrayList<String>>>>>call, Response<Map<String, Map<String, Map<String, ArrayList<String>>>>> response) {

                prepareListData(response.body());
                Log.d("Test", "Sucess !");
//                fragmentTransaction.remove(f).commit();
            }

            @Override
            public void onFailure(Call<Map<String, Map<String, Map<String, ArrayList<String>>>>>call, Throwable t) {
                prepareListData(null);
                Log.d("Test", t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh:
                if(isInternetAvailable()) {
                    getData();
                    Log.d("Test", "Connected  !");
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Lỗi");
                    builder.setMessage("Vui lòng kiểm tra kết nối !");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo
                netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }
    private void prepareListData(Map<String, Map<String, Map<String, ArrayList<String>>>> list) {
        Fragment f = getFragmentManager().findFragmentByTag("loading");

        if(f!=null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(f).commit();

        }
        expListView.setVisibility(View.VISIBLE);

        presenter.setData(list);
        listDataHeader.clear();
        listDataChild.clear();

        if(list == null) return;
        Utils<Map<String, Map<String, ArrayList<String>>>> utils = new Utils<>();
        ArrayList<String> keys = utils.getAllKeys(list);

        // Adding child data
        for(String key : keys){
            listDataHeader.add(key);
            List<String> header = new ArrayList<>();
            Utils<Map<String, ArrayList<String>>> utils1 = new Utils<>();
            ArrayList<String> key1s = utils1.getAllKeys(list.get(key));
            for(String key1 : key1s){
                header.add(key1);
            }
            listDataChild.put(key, header);
        }

        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetai(Map<String, ArrayList<String>> map) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        DataExtral data = new DataExtral(map);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}

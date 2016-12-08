package com.nanosic.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private GridView gridView;
    private BaseAdapter adapter;
    private ArrayList<HashMap<String,Object>> remoteMaps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i=0; i<20; i++) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("keyName", "i" + "click");
            remoteMaps.add(map);
        }

        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));


        adapter = new SimpleAdapter(this, remoteMaps, R.layout.grid_item_remote_panel, new String[]{"keyName"}, new int[]{R.id.button});
        gridView.setAdapter(adapter);
        gridView.setOnKeyListener(null);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // RemoteHub.getService().sendKey(remoteKeys.get(position).keyValue);
                Log.i(TAG, "onItemClick: ");
            }
        });
    }
}

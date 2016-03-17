package com.nanosic.simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    final private String TITLE = "title";
    final private String INFO = "info";
    private  ListView mListView;
    SimpleAdapter mSimpleAdapter;
    ArrayList<HashMap<String, Object>> listItem;
    String[] from = {TITLE, INFO};
    int[] to = {R.id.text1, R.id.text2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItem = new ArrayList<>();
        for (int i=0; i< 100; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put(TITLE, "title" + i);
            map.put(INFO, "info" + i);
            listItem.add(map);
        }

        mListView = (ListView) findViewById(R.id.lv);
        mSimpleAdapter = new SimpleAdapter(this, listItem, R.layout.item, from, to);
        mListView.setAdapter(mSimpleAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setTitle("you click item" + position);
            }
        });
    }
}

package com.nanosic.learnlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    private List<String>list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("lin1");
        list.add("lin2");
        list.add("lin3");
        list.add("lin4");
        list.add("lin5");
        listView = new ListView(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        setContentView(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

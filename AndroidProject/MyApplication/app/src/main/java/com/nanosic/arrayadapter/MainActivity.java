package com.nanosic.arrayadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> mArrayAdapter;
    List<String> mListString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*generate Adapter*/
        mListString = new ArrayList<>();
        for (int i=0; i<100; i++) {
            mListString.add("item" + i);
        }
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, mListString);

        /*set Adapter*/
        ListView mListView = (ListView) findViewById(R.id.lv);
        try {
            assert mListView != null;
            mListView.setAdapter(mArrayAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setTitle("you click item" + position);
            }
        });






    }
}

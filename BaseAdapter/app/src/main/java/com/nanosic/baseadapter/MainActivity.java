package com.nanosic.baseadapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private ArrayList<HashMap<String, Object>> mListItem;
    final private String TITLE = "title";
    final private String INFO = "info";

    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (int i=0; i<100; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put(TITLE, TITLE + i);
            map.put(INFO, INFO + i);
            list.add(map);
        }
        return list;
    }


    private class MyAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        public MyAdapter(Context c) {
            this.mLayoutInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount() {
            return mListItem.size();
        }

        @Override
        public Object getItem(int position) {
            Log.i("MainActivity", "getItem, position=" + position);
            return mListItem.get(position);
        }

        @Override
        public long getItemId(int position) {
            Log.i("MainActivity", "getItemId, position=" + position);
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder  holder;
            Log.v("MainActivity", "getView " + position + " " + convertView);
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.item, null);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.text1);
                holder.info = (TextView) convertView.findViewById(R.id.text2);
                holder.bt = (Button) convertView.findViewById(R.id.bt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.title.setText(mListItem.get(position).get(TITLE).toString());
            holder.info.setText(mListItem.get(position).get(INFO).toString());
            holder.bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("MainActivity", "you click item" + position);
                }
            });

            return convertView;
        }

        public final class ViewHolder {
            public TextView title;
            public TextView info;
            public Button bt;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListItem = getData();
        /*find ListView*/
        mListView = (ListView) findViewById(R.id.lv);
        final MyAdapter myAdapter = new MyAdapter(this);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MainActivity", "you click item, position:" + position + " id:" + id);
                Log.i("MainActivity", String.format("getItem(position=%d)=%s", position, myAdapter.getItem(position)));
                Log.i("MainActivity", String.format("getItem(id=%d)=%s", id, myAdapter.getItem((int)id)));
            }
        });
    }
}

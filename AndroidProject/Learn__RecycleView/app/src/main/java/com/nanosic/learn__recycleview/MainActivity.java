package com.nanosic.learn__recycleview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity {
    private LinearLayout top_bar_linear_back;
    private TextView top_bar_title;
    private RecyclerView recyclerView_one;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    class TestRecyclerAdapter extends RecyclerView.Adapter<TestRecyclerAdapter.ViewHolder> {
        private LayoutInflater mInflater;
        private String[] mTitles = null;

        public TestRecyclerAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
            this.mTitles = new String[20];
            for (int i = 0; i < 20; i++) {
                int index = i + 1;
                mTitles[i] = "item" + index;
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_recycler_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(TestRecyclerAdapter.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView item_tv;

            public ViewHolder(View view) {
                super(view);
                item_tv = (TextView) view.findViewById(R.id.item_tv);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

//        recyclerView_one = (RecyclerView)findViewById(R.id.recyclerView_one);
//        recyclerView_one.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//        recyclerView_one.setLayoutManager(mLayoutManager);
//        mAdapter = new TestRecyclerAdapter(this);
//        recyclerView_one.setAdapter(mAdapter);
        String str = "1234ABF000000";
        byte[] srtbyte = null;
        try {
            srtbyte = str.getBytes("GB2312");
        } catch (UnsupportedEncodingException e) {

        }

        Log.d("onCreate", "srtbyte.length="+srtbyte.length);
        Log.d("onCreate", String.format("srtbyte[0]=%d", srtbyte[0]));
        Log.d("onCreate", String.format("srtbyte[1]=%d", srtbyte[1]));
        Log.d("onCreate", String.format("srtbyte[2]=%d", srtbyte[2]));
        Log.d("onCreate", String.format("srtbyte[3]=%d", srtbyte[3]));
        Log.d("onCreate", String.format("srtbyte[4]=%d", srtbyte[4]));
        Log.d("onCreate", String.format("srtbyte[5]=%d", srtbyte[5]));
        Log.d("onCreate", String.format("srtbyte[6]=%d", srtbyte[6]));
        Log.d("onCreate", String.format("srtbyte[7]=%d", srtbyte[7]));
        Log.d("onCreate", String.format("srtbyte[8]=%d", srtbyte[8]));
        Log.d("onCreate", String.format("srtbyte[9]=%d", srtbyte[9]));
        Log.d("onCreate", String.format("srtbyte[10]=%d", srtbyte[10]));
        Log.d("onCreate", String.format("srtbyte[11]=%d", srtbyte[11]));
        Log.d("onCreate", String.format("srtbyte[12]=%d", srtbyte[12]));

        String res = null;
        try {
            res = new String(srtbyte, "GB2312");
        } catch (UnsupportedEncodingException e) {

        }
        Log.d("onCreate", "res="+res);
    }
}

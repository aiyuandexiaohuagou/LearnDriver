package com.nanosic.recyclerview2_gridlayoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new HomeAdapter(mDatas);
        mAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                String a = mAdapter.getmDatas().get(pos);
                String b = a.substring(4, a.length());
                Log.d("TAG", "b="+b);
                if (Integer.parseInt(b) % 2 == 0) {
                    mAdapter.getmDatas().remove(pos);
                    mAdapter.notifyItemRemoved(pos);
                } else {
                    mAdapter.getmDatas().add(pos, "item" + new Random().nextInt(1000) + 1);
                    mAdapter.notifyItemInserted(pos);
                }
            }

            @Override
            public void onItemLongClick(View view, int pos) {

            }
        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i=0; i<20; i++) {
            mDatas.add("item" + i);
        }
    }

}

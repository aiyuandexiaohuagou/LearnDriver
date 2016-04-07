package com.nanosic.collapsingtoolbarlayout;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsing_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setTitle("I am Title");
    }
}

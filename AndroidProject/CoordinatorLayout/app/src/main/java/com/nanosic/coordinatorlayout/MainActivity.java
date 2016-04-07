package com.nanosic.coordinatorlayout;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import junit.framework.Assert;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionBar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello, I'm a Snackbar!", Snackbar.LENGTH_INDEFINITE).setAction("Stop".toLowerCase(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("cancel");
                    }
                }).show();
            }
        });
    }
}

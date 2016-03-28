package com.example.administrator.learnbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private MainActivityReceiver rc;

    public class MainActivityReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
           et.setText("Receive Broadcast: getAction()=" + intent.getAction() + ", " + "getStringExtra()=" + intent.getStringExtra("msg"), TextView.BufferType.NORMAL);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Intent i = new Intent(this, MyService.class);

        findViewById(R.id.btn_StartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rc = new MainActivityReceiver();
                IntentFilter filter = new IntentFilter();
                filter.addAction("com.example.administrator.learnbroadcast.BROADCAST_FROM_MYSERVICE");
                registerReceiver(rc, filter);

                startService(i);
            }
        });

        findViewById(R.id.btn_StopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(rc);
                stopService(i);
            }
        });
        et = (EditText) findViewById(R.id.et_Text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

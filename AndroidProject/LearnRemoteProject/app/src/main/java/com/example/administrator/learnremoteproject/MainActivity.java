package com.example.administrator.learnremoteproject;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final private String TAG = "MainActivity";
    private Button btn_startService;
    private Button btn_stopService;
    private Button btn_BindRemoteService;
    private Button btn_UnBindRemoteService;
    private Button btn_GetName;
    private Intent intent;

    private IMyAidlInterface binder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            binder = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
            binder = null;
        }
    } ;

    private IActivityCallback.Stub mCallback = new IActivityCallback.Stub() {
        @Override
        public void onNotifySet(MyRect r) throws RemoteException {
            Log.d(TAG, String.format("onNotifySet(%d, %d, %d, %d)", r.left, r.top, r.right, r.bottom));
        }
    };

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
        intent = new Intent(MainActivity.this, MyRemoteService.class);

        btn_startService = (Button) findViewById(R.id.btn_startService);
        btn_startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        btn_stopService = (Button) findViewById(R.id.btn_stopService);
        btn_stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        btn_BindRemoteService = (Button) findViewById(R.id.btn_BindRemoteService);
        btn_BindRemoteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b =  bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
                Log.i(TAG, "onClick: bindService, return : " + b);
            }
        });

        btn_UnBindRemoteService = (Button) findViewById(R.id.btn_UnBindRemoteService);
        btn_UnBindRemoteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder != null) {
                    Log.i(TAG, "onClick: unbindService");
                    unbindService(serviceConnection);
                    binder = null;
                }
            }
        });

        btn_GetName = (Button) findViewById(R.id.btn_GetName);
        btn_GetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = null;
                try {
                    name = binder.getName();
                    Log.d(TAG, "ProcessId=" + android.os.Process.myPid());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "name:" + name, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_SetRect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int l = random.nextInt();
                int t = random.nextInt();
                int r = random.nextInt();
                int b = random.nextInt();

                try {
                    binder.setRect(new MyRect(l, t, r, b));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_GetRect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MyRect rect = binder.getRect();
                    Log.d(TAG, String.format("rect is (%d, %d, %d, %d)", rect.left, rect.top, rect.right, rect.bottom));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });

        findViewById(R.id.btn_RegisterCallback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    binder.registerCallback(mCallback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_UnRegisterCallback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    binder.unRegisterCallback(mCallback);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

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

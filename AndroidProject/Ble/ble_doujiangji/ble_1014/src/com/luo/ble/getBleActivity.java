package com.luo.ble;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class getBleActivity extends Activity {
	private final static String TAG = "GetBle";
	private BluetoothAdapter mBluetoothAdapter;
	private BluetoothManager mBluetoothManager;
	private List<BleDevice> mListBleDevices;
	
	private boolean mScanning;
	private Handler mHandler;
	
	//Stops scanning after 10 seconds
	private final static int SCAN_PERIOD = 10000;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //check whether BLE is supported on this device
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE))
        {
        	Toast.makeText(this, "ble not supported", Toast.LENGTH_SHORT).show();
        	finish();
        } 
        
        //init BLE device list
        mListBleDevices = new ArrayList<BleDevice>();
        
    }
	
	public boolean InitBle()
	{
		Log.i(TAG, "get mBluetoothManager");
		//get a Adapter through BluetoothManager
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        if(null == mBluetoothManager)
        {
        	Log.e(TAG, "null == mBluetoothManager");
        	return false;
        }
        
        Log.i(TAG, "get mBluetoothAdapter");
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if(null == mBluetoothAdapter)
        {
        	Log.e(TAG, "null == mBluetoothAdapter");
        	return false;
        }
        
        if(!mBluetoothAdapter.isEnabled())
        {
        	Log.i(TAG, "enable mBluetoothAdapter");
        	mBluetoothAdapter.enable();
        }
        
		
        return true;
	}
	
	public void scanBleDevice(final boolean enable)
	{
		if(enable)
		{
			//stops scanning after a pre-defined scan period
			mHandler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.i(TAG, "stopLeScan");
					mScanning = false;
					mBluetoothAdapter.stopLeScan(mLeScanCallback);
				}
			}, SCAN_PERIOD);
			
			Log.i(TAG, "startLeScan");
			mScanning = true;
			mBluetoothAdapter.startLeScan(mLeScanCallback);
		}
		else
		{
			Log.i(TAG, "stopLeScan");
			mScanning = false;
			mBluetoothAdapter.stopLeScan(mLeScanCallback);
		}		
	}
	
	private BluetoothAdapter.LeScanCallback mLeScanCallback =
			new BluetoothAdapter.LeScanCallback() {
				
				@Override
				public void onLeScan(final BluetoothDevice arg0, final int arg1, final byte[] arg2) {
					// TODO Auto-generated method stub
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Log.i(TAG, "callback "+ arg0.getName() + " rssi = " + arg1);
							mListBleDevices.add(new BleDevice(arg0, arg1, arg2));
						}
					});
				}
			};

}

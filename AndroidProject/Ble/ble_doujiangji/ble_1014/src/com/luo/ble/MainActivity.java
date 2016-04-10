package com.luo.ble;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {
	private final static String TAG = "GetBle";
	private BluetoothManager mBluetoothManager;
	private BluetoothAdapter mBluetoothAdapter;	
	private List<BleDevice> mListBleDevices;
	private Button mButton;

	private boolean mScanning;
	private Handler mHandler;

	// Stops scanning after 10 seconds
	private final static int SCAN_PERIOD = 10000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		mHandler = new Handler();
		Log.i(TAG, "init start");

		//1. init BLE device list
		mListBleDevices = new ArrayList<BleDevice>();

		//2. get BluetoothAdapter
		InitBle();

		Log.i(TAG, "init end");

		//3. set Button OnClick Processing function
		mButton = (Button) findViewById(R.id.scan);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.i(TAG, "onClick start");
				
				//4. when you touch this button, then starting scan BLE device
				scanBleDevice(true);
				Log.i(TAG, "onClick end");
			}
		});


	}



	public boolean InitBle() {
		Log.i(TAG, "get mBluetoothManager");
		// get a Adapter through BluetoothManager
		mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		if (null == mBluetoothManager) {
			Log.e(TAG, "null == mBluetoothManager");
			return false;
		}

		Log.i(TAG, "get mBluetoothAdapter");
		mBluetoothAdapter = mBluetoothManager.getAdapter();
		if (null == mBluetoothAdapter) {
			Log.e(TAG, "null == mBluetoothAdapter");
			return false;
		}

		//if Bluetooth is power off, then power on it.
		Log.i(TAG, "mBluetoothAdapter enable");
		if (!mBluetoothAdapter.isEnabled()) {
			Log.i(TAG, "has enable mBluetoothAdapter");
			mBluetoothAdapter.enable();
		}

		Log.i(TAG, "InitBle end");

		return true;
	}
	
	

	public void scanBleDevice(final boolean enable) {
		Log.i(TAG, "scanBleDevice start");
		if (enable) {
			
			// stops scanning after a pre-defined scan period
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Log.i(TAG, "stopLeScan");
					setScanning(false);
					mBluetoothAdapter.stopLeScan(mLeScanCallback);
				}
			}, SCAN_PERIOD);

			Log.i(TAG, "startLeScan");
			setScanning(true);
			//5. start scan, with a scan callback function 
			mBluetoothAdapter.startLeScan(mLeScanCallback);
		} else {
			Log.i(TAG, "stopLeScan");
			setScanning(false);
			mBluetoothAdapter.stopLeScan(mLeScanCallback);
		}
		Log.i(TAG, "scanBleDevice end");
	}

	public boolean isScanning() {
		return mScanning;
	}

	public void setScanning(boolean mScanning) {
		this.mScanning = mScanning;
	}

	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

		@Override
		public void onLeScan(final BluetoothDevice bleDevice, final int rssi, final byte[] scanRecord) {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					/*
					6. if there are BLE devices around, program will come to here to execute this callback function.
					we can print device's name/rssi/scanRecord.
					then I put it into a list, and I will process it in my mHandler.handleMessage later.
					*/
					Log.i(TAG, "callback " + bleDevice.getName() + " rssi = " + rssi + " scanRecord" + String.valueOf(scanRecord));
					printHexString(scanRecord);
					// 扫描回调函数将扫描到的远端BLE设备添加入设备列表
					mListBleDevices.add(new BleDevice(bleDevice, rssi, scanRecord));
				}
			});
		}
	};
	
	public static void printHexString( byte[] b) {  
		   for (int i = 0; i < b.length; i++) { 
		     String hex = Integer.toHexString(b[i] & 0xFF); 
		     if (hex.length() == 1) { 
		       hex = '0' + hex; 
		     } 
		     Log.i(TAG, hex.toUpperCase() ); 
		   } 

		}

}

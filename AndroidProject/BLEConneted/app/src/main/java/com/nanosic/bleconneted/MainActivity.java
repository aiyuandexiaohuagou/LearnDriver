package com.nanosic.bleconneted;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MyBle";
    private static final String UUID_KEY_DATA = "1111"; //"00002a00-0000-1000-8000-00805f9b34fb";
    private BluetoothAdapter mBluetoothAdapter;
    private List<BluetoothDevice> mListBluetoothDevice;
    private List<BluetoothDevice> mListBleDevices;
    private List<Map<String, String>> mStringDevices;
    private BluetoothDevice mBleDevice;
    private BluetoothGatt mBluetoothGatt = null;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton mButton;
    private boolean mScanning = false;
    private Snackbar snackbarScan;
    private Snackbar snackbarStop;
    private ListView listView;
    private SimpleAdapter listadapter;

    private final BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.d(TAG, "-onConnectionStateChange:" +  gatt.getDevice().getName() + "--" + "status=" + status + ", newState=" + newState);
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.d(TAG, "STATE_CONNECTED, call discoverServices");
                //9. when our phone successfully connect to the BLE device, program will come too here.
                mBluetoothGatt.discoverServices();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.d(TAG, "-onServicesDiscovered:" + gatt.getDevice().getName() + "--" + "status=" + status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
				displayGattServices(mBluetoothGatt.getServices());
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG, "--onCharacteristicRead-" + gatt.getDevice().getName() + "--" + new String(characteristic.getValue()));
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG, "-onCharacteristicWrite--" + gatt.getDevice().getName() + "--" + new String(characteristic.getValue()));
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.i(TAG, "onCharacteristicChanged---" + gatt.getDevice().getName() + "--"  + new String(characteristic.getValue()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
//        snackbarScan = Snackbar.make(coordinatorLayout, "Scanning...", Snackbar.LENGTH_INDEFINITE);
//        snackbarStop = Snackbar.make(coordinatorLayout, "Stop", Snackbar.LENGTH_LONG);

        listView = (ListView) findViewById(R.id.listView);
        mStringDevices = new ArrayList<>();
        listadapter = new SimpleAdapter(MainActivity.this, mStringDevices, R.layout.vlist, new String[]{"name"}, new int[] {R.id.name});
        listView.setAdapter(listadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListBleDevices != null && mListBleDevices.size() != 0) {
                    Log.i(TAG, "----");
                    //7. 从设备列表中取出远端BLE设备
                    mBleDevice = mListBleDevices.get(position);
                    if (mBleDevice != null) {
                        //8. connect to that BLE device, and set callback function.
                        Log.i(TAG, "connect " + mBleDevice.getName());
                        mBluetoothGatt = mBleDevice.connectGatt(getApplicationContext(), false, mBluetoothGattCallback);
                    }
                }
            }
        });

        Log.i(TAG, "init start");

        //1. init BLE device list
        mListBleDevices = new ArrayList<>();

        //2. get BluetoothAdapter
        InitBle();

        Log.i(TAG, "init end");

        //3. set Button OnClick Processing function
//        mButton = (FloatingActionButton) findViewById(R.id.floatingActionBar);
//        mButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Log.i(TAG, "onClick start");
//
//                //4. when you touch this button, then starting scan BLE device
//
//                Log.i(TAG, "onClick end");
//            }
//        });
    }

    public boolean InitBle() {
        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        Log.i(TAG, "get BLE is supported on the device");
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE is supported on the device", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "InitBle: BLE is supported on the device");
            return false;
        }

        Log.i(TAG, "get mBluetoothManager");
        // get a Adapter through BluetoothManager
        BluetoothManager mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        if (null == mBluetoothManager) {
            Toast.makeText(this, "BluetoothManager is null", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "InitBle: BluetoothManager is null");
            return false;
        }

        Log.i(TAG, "get mBluetoothAdapter");
        // Checks if Bluetooth is supported on the device.
        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (null == mBluetoothAdapter) {
            Log.e(TAG, "null == mBluetoothAdapter");
            Toast.makeText(this, "Bluetooth is not supported on the device", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "InitBle: Bluetooth is not supported on the device");
            return false;
        }

        //if Bluetooth is power off, then power on it.
        Log.i(TAG, "mBluetoothAdapter enable");
        if (!mBluetoothAdapter.isEnabled()) {
            Log.i(TAG, "has enable mBluetoothAdapter");
            mBluetoothAdapter.enable();
        }


        mListBleDevices.clear();
        mStringDevices.clear();
        mListBluetoothDevice = mBluetoothManager.getConnectedDevices(BluetoothProfile.GATT);
        Log.i(TAG, "InitBle: mListBluetoothDevice.size()=" + mListBluetoothDevice.size());
        for(int i=0; i<mListBluetoothDevice.size(); i++) {
            BluetoothDevice dev = mListBluetoothDevice.get(i);
            int deviceType = dev.getType();
            if (BluetoothDevice.DEVICE_TYPE_LE == deviceType) {
                mListBleDevices.add(dev);
                Map<String, String> map = new HashMap<>();
                map.put("name", dev.getName());
                mStringDevices.add(map);
            }
        }
        Log.i(TAG, "InitBle: mListBleDevices.size()=" + mListBleDevices.size());
        Log.i(TAG, "InitBle end");

        return true;
    }

	private void displayGattServices(List<BluetoothGattService> gattServices) {
		if (gattServices == null)
			return;
        Log.i(TAG, "---gattServices.size():" + gattServices.size());
		for (int i=0; i < gattServices.size(); i++) {
            final BluetoothGattService gattService = gattServices.get(i);
			int type = gattService.getType();
            Log.i(TAG, String.format("gattServices[%d]", i));
			Log.i(TAG, "        service type:" + type + ":" + Utils.getServiceType(type));
			//Log.i(TAG, "        included Services size:" + gattService.getIncludedServices().size());
			Log.i(TAG, "        service uuid:" + gattService.getUuid());

			List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            Log.i(TAG, "        gattCharacteristics.size():" + gattCharacteristics.size());
			for (int j=0; j< gattCharacteristics.size(); j++) {
                final BluetoothGattCharacteristic gattCharacteristic = gattCharacteristics.get(j);
                Log.i(TAG, String.format("            Characteristic[%d]", j));
                Log.i(TAG, "                Characteristic uuid:" + gattCharacteristic.getUuid());

                int permission = gattCharacteristic.getPermissions();
				Log.i(TAG, "                Characteristic permission:" + String.format("0x%02x", permission));

				int property = gattCharacteristic.getProperties();
				Log.i(TAG, "                Characteristic property:" + String.format("0x%02x", property));

				byte[] data = gattCharacteristic.getValue();
				if (data != null && data.length > 0) {
                    Log.i(TAG, "                Characteristic value:" + new String(data));
				} else {
                    Log.i(TAG, "                Characteristic value:" + "null");
                }

				if (gattCharacteristic.getUuid().toString().equals(UUID_KEY_DATA)) {
					mHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
                            mBluetoothGatt.readCharacteristic(gattCharacteristic);
						}
					}, 500);

                    mBluetoothGatt.setCharacteristicNotification(gattCharacteristic, true);
					gattCharacteristic.setValue("The name is changed");
                    Log.i(TAG, "writeCharacteristic: send data in" + UUID_KEY_DATA);
                    mBluetoothGatt.writeCharacteristic(gattCharacteristic);
				}

				// -----Descriptors的字段信息-----//
				List<BluetoothGattDescriptor> gattDescriptors = gattCharacteristic.getDescriptors();
                Log.i(TAG, "                gattDescriptors.size():" + gattDescriptors.size());
				for (int k=0; k<gattDescriptors.size(); k++) {
                    BluetoothGattDescriptor gattDescriptor = gattDescriptors.get(k);
                    Log.i(TAG, "                    gattDescriptor[" + k + "]:");
					Log.i(TAG, "                    gattDescriptor uuid:" + gattDescriptor.getUuid());
					int descPermission = gattDescriptor.getPermissions();
                    Log.i(TAG, "                    gattDescriptor permission:" + String.format("0x%02x", descPermission));

					byte[] desData = gattDescriptor.getValue();
					if (desData != null && desData.length > 0) {
                        Log.i(TAG, "                    gattDescriptor value:" + new String(desData));
					} else {
                        Log.i(TAG, "                    gattDescriptor value:" + "null");
                    }
				}
			}
		}

	}


    public static void printHexString( byte[] b) {
        for (byte aB : b) {
            String hex = Integer.toHexString(aB & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            Log.i(TAG, hex.toUpperCase());
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            Log.i(TAG, "handleMessage");
//            switch (message.what) {
//                case 0: {
//                    if (mListBleDevices != null) {
//                        Log.i(TAG, "----");
//                        //7. 从设备列表中取出远端BLE设备
//                        mBleDevice = mListBleDevices.get(0);
//                        if (mBleDevice != null) {
//                            //8. connect to that BLE device, and set callback function.
//                            Log.i(TAG, "handleMessage: connect " + mBleDevice.getBleDevice().getName());
//                            mBluetoothGatt = mBleDevice.getBleDevice().connectGatt(getApplicationContext(), false, mBluetoothGattCallback);
//                        }
//                    }
//                }
//                break;
//                default:
//                    break;
//            }
        }
    };

}

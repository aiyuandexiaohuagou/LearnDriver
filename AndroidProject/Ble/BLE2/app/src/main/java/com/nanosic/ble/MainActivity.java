package com.nanosic.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "GetBle";
    private static final String UUID_KEY_DATA = "temp";
    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private List<BleDevice> mListBleDevices;
    private BleDevice mBleDevice;
    private BluetoothGatt mBluetoothGatt = null;

    private Button mButton;
    private boolean mScanning;

    private final BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.d(TAG, "-onConnectionStateChange--");
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.d(TAG, "STATE_CONNECTED");
                //9. when our phone successfully connect to the BLE device, program will come too here.
                mBluetoothGatt.discoverServices();
            }

        }

        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.d(TAG, "-onServicesDiscovered--");
            if (status == BluetoothGatt.GATT_SUCCESS) {
				displayGattServices(mBluetoothGatt.getServices());
            }
        }

        public void onCharacteristicRead(BluetoothGatt gatt, android.bluetooth.BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG, "--onCharacteristicRead-");
        }

        public void onCharacteristicWrite(BluetoothGatt gatt, android.bluetooth.BluetoothGattCharacteristic characteristic, int status) {
            Log.d(TAG, "-onCharacteristicWrite--");
        }

        public void onCharacteristicChanged(BluetoothGatt gatt, android.bluetooth.BluetoothGattCharacteristic characteristic) {
            Log.i(TAG, "onCharacteristicChanged---");
        }
    };

    private Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            Log.i(TAG, "handleMessage");
            switch (message.what) {
                case 0: {
                    if (mListBleDevices != null) {
                        Log.i(TAG, "----");
                        //7. 从设备列表中取出远端BLE设备
                        mBleDevice = mListBleDevices.get(0);
                        if (mBleDevice != null) {
                            //8. connect to that BLE device, and set callback function.
                            mBluetoothGatt = mBleDevice.getBleDevice().connectGatt(getApplicationContext(), false, mBluetoothGattCallback);

                        }

                    }

                }
                break;

                default:
                    break;
            }
        }
    };

    // Stops scanning after 10 seconds
    private final static int SCAN_PERIOD = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);


        Log.i(TAG, "init start");

        //1. init BLE device list
        mListBleDevices = new ArrayList<BleDevice>();

        //2. get BluetoothAdapter
        InitBle();

        Log.i(TAG, "init end");

        //3. set Button OnClick Processing function
        mButton = (Button) findViewById(R.id.scan);
        mButton.setOnClickListener(new View.OnClickListener() {

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
        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        Log.i(TAG, "get BLE is supported on the device");
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {

            Log.e(TAG, "BLE is supported on the device");
            return false;
        }

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

                    Message message = Message.obtain();
                    message.what = 0;
                    message.obj = "luo";
                    if (mHandler != null) {
                        mHandler.sendMessage(message);
                        Log.i(TAG, "sendMessage");
                    }
                }
            });
        }
    };

    public static void printHexString( byte[] b) {
        for (byte aB : b) {
            String hex = Integer.toHexString(aB & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            Log.i(TAG, hex.toUpperCase());
        }

    }

	private void displayGattServices(List<BluetoothGattService> gattServices) {
		if (gattServices == null)
			return;

		for (BluetoothGattService gattService : gattServices) {
			int type = gattService.getType();
			Log.e(TAG, "-->service type:" + Utils.getServiceType(type));
			Log.e(TAG, "-->includedServices size:" + gattService.getIncludedServices().size());
			Log.e(TAG, "-->service uuid:" + gattService.getUuid());

			List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
			for (final BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
				Log.e(TAG, "---->char uuid:" + gattCharacteristic.getUuid());

				int permission = gattCharacteristic.getPermissions();
				Log.e(TAG, "---->char permission:" + Utils.getCharPermission(permission));

				int property = gattCharacteristic.getProperties();
				Log.e(TAG, "---->char property:" + Utils.getCharPropertie(property));

				byte[] data = gattCharacteristic.getValue();
				if (data != null && data.length > 0) {
					Log.e(TAG, "---->char value:" + new String(data));
				}

				if (gattCharacteristic.getUuid().toString().equals(UUID_KEY_DATA)) {
					mHandler.postDelayed(new Runnable() {
						@Override
						public void run() {
                            mBluetoothGatt.readCharacteristic(gattCharacteristic);
						}
					}, 500);

                    mBluetoothGatt.setCharacteristicNotification(gattCharacteristic, true);
					gattCharacteristic.setValue("send data->");
                    mBluetoothGatt.writeCharacteristic(gattCharacteristic);
				}

				// -----Descriptors鐨勫瓧娈典俊鎭�-----//
				List<BluetoothGattDescriptor> gattDescriptors = gattCharacteristic.getDescriptors();
				for (BluetoothGattDescriptor gattDescriptor : gattDescriptors) {
					Log.e(TAG, "-------->desc uuid:" + gattDescriptor.getUuid());
					int descPermission = gattDescriptor.getPermissions();
					Log.e(TAG, "-------->desc permission:" + Utils.getDescPermission(descPermission));

					byte[] desData = gattDescriptor.getValue();
					if (desData != null && desData.length > 0) {
						Log.e(TAG, "-------->desc value:" + new String(desData));
					}
				}
			}
		}//

	}
}

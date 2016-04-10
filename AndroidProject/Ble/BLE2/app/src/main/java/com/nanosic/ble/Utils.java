package com.nanosic.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;

/**
 * Created by Administrator on 2016/4/10.
 */
public class Utils {
    public static String getServiceType(int type) {
        String ServiceType = "No find";
        switch (type) {
            case BluetoothGattService.SERVICE_TYPE_PRIMARY:
                ServiceType = "BluetoothGattService.SERVICE_TYPE_PRIMARY: Primary service";
            break;
            case BluetoothGattService.SERVICE_TYPE_SECONDARY:
                ServiceType = "BluetoothGattService.SERVICE_TYPE_SECONDARY: Secondary service (included by primary services)";
                break;
            default :
                break;
        }

        return ServiceType;
    }


    public static String getCharPermission(int permission) {
            String CharPermission = "No find";
            switch (permission) {
                /**
                 * Characteristic read permission
                 */
                case BluetoothGattCharacteristic.PERMISSION_READ:
                    CharPermission = "BluetoothGattCharacteristic.PERMISSION_READ: Characteristic read permission";
                    break;
                /**
                 * Characteristic permission: Allow encrypted read operations
                 */
                case BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED:
                    CharPermission = "BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED: Characteristic permission: Allow encrypted read operations";
                    break;
                /**
                 * Characteristic permission: Allow reading with man-in-the-middle protection
                 */
                case BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED_MITM:
                    CharPermission = "BluetoothGattCharacteristic.PERMISSION_READ_ENCRYPTED_MITM: Characteristic permission: Allow reading with man-in-the-middle protection";
                    break;
                /**
                 * Characteristic write permission
                 */
                case BluetoothGattCharacteristic.PERMISSION_WRITE:
                    CharPermission = "BluetoothGattCharacteristic.PERMISSION_WRITE: Characteristic write permission";
                    break;
                /**
                 * Characteristic permission: Allow encrypted writes
                 */
                case BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED:
                    CharPermission = "BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED: Characteristic permission: Allow encrypted writes";
                    break;
                /**
                 * Characteristic permission: Allow encrypted writes with man-in-the-middle
                 * protection
                 */
                case BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED_MITM:
                    CharPermission = "BluetoothGattCharacteristic.PERMISSION_WRITE_ENCRYPTED_MITM: Characteristic permission: Allow encrypted writes with man-in-the-middle protection";
                    break;
                /**
                 * Characteristic permission: Allow signed write operations
                 */
                case BluetoothGattCharacteristic.PERMISSION_WRITE_SIGNED:
                    CharPermission = "BluetoothGattCharacteristic.PERMISSION_WRITE_SIGNED: Characteristic permission: Allow signed write operations";
                    break;
                /**
                 * Characteristic permission: Allow signed write operations with
                 * man-in-the-middle protection
                 */
                case BluetoothGattCharacteristic.PERMISSION_WRITE_SIGNED_MITM:
                    CharPermission = "BluetoothGattCharacteristic.PERMISSION_WRITE_SIGNED_MITM: Characteristic permission: Allow signed write operations with man-in-the-middle protection";
                    break;
                default :
                    break;
            }

            return CharPermission;
    }

    public static String getCharPropertie(int property) {
        String CharPropertie = "No find";
        switch (property) {
            /**
             * Characteristic proprty: Characteristic is broadcastable.
             */
            case BluetoothGattCharacteristic.PROPERTY_BROADCAST:
                CharPropertie = "BluetoothGattCharacteristic.PROPERTY_BROADCAST: Characteristic proprty: Characteristic is broadcastable.";
                break;
            /**
             * Characteristic property: Characteristic is readable.
             */
            case BluetoothGattCharacteristic.PROPERTY_READ:
                CharPropertie = "BluetoothGattCharacteristic.PROPERTY_READ: Characteristic property: Characteristic is readable.";
                break;
            /**
             * Characteristic property: Characteristic can be written without response.
             */
            case BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE:
                CharPropertie = "BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE: Characteristic property: Characteristic can be written without response.";
                break;
            /**
             * Characteristic property: Characteristic can be written.
             */
            case BluetoothGattCharacteristic.PROPERTY_WRITE:
                CharPropertie = "BluetoothGattCharacteristic.PROPERTY_WRITE: Characteristic property: Characteristic can be written.";
                break;
            /**
             * Characteristic property: Characteristic supports notification
             */
            case BluetoothGattCharacteristic.PROPERTY_NOTIFY:
                CharPropertie = "BluetoothGattCharacteristic.PROPERTY_NOTIFY: Characteristic property: Characteristic supports notification";
                break;
            /**
             * Characteristic property: Characteristic supports indication
             */
            case BluetoothGattCharacteristic.PROPERTY_INDICATE:
                CharPropertie = "BluetoothGattCharacteristic.PROPERTY_INDICATE: Characteristic property: Characteristic supports indication";
                break;
            /**
             * Characteristic property: Characteristic supports write with signature
             */
            case BluetoothGattCharacteristic.PROPERTY_SIGNED_WRITE:
                CharPropertie = "BluetoothGattCharacteristic.PROPERTY_SIGNED_WRITE: Characteristic property: Characteristic supports write with signature";
                break;
            default :
                break;
        }

        return CharPropertie;
    }

    public static String getDescPermission(int descPermission) {
        String DescPermission = "No find";
        switch (descPermission) {
            /**
             * Descriptor read permission
             */
            case BluetoothGattDescriptor.PERMISSION_READ:
                DescPermission = "BluetoothGattDescriptor.PERMISSION_READ: Descriptor read permission";
                break;
            /**
             * Descriptor permission: Allow encrypted read operations
             */
            case BluetoothGattDescriptor.PERMISSION_READ_ENCRYPTED:
                DescPermission = "BluetoothGattDescriptor.PERMISSION_READ_ENCRYPTED: Descriptor permission: Allow encrypted read operations";
                break;
            /**
             * Descriptor permission: Allow reading with man-in-the-middle protection
             */
            case BluetoothGattDescriptor.PERMISSION_READ_ENCRYPTED_MITM:
                DescPermission = "BluetoothGattDescriptor.PERMISSION_READ_ENCRYPTED_MITM: Descriptor permission: Allow reading with man-in-the-middle protection";
                break;
            /**
             * Descriptor write permission
             */
            case BluetoothGattDescriptor.PERMISSION_WRITE:
                DescPermission = "BluetoothGattDescriptor.PERMISSION_WRITE: Descriptor write permission";
                break;
            /**
             * Descriptor permission: Allow encrypted writes
             */
            case BluetoothGattDescriptor.PERMISSION_WRITE_ENCRYPTED:
                DescPermission = "BluetoothGattDescriptor.PERMISSION_WRITE_ENCRYPTED: Descriptor permission: Allow encrypted writes";
                break;
            /**
             * Descriptor permission: Allow encrypted writes with man-in-the-middle
             *
             */
            case BluetoothGattDescriptor.PERMISSION_WRITE_ENCRYPTED_MITM:
                DescPermission = "BluetoothGattDescriptor.PERMISSION_WRITE_ENCRYPTED_MITM: Descriptor permission: Allow encrypted writes with man-in-the-middle protection";
                break;
            /**
             * Descriptor permission: Allow signed write operations
             */
            case BluetoothGattDescriptor.PERMISSION_WRITE_SIGNED:
                DescPermission = "BluetoothGattDescriptor.PERMISSION_WRITE_SIGNED: Descriptor permission: Allow signed write operations";
                break;
            /**
             * Descriptor permission: Allow signed write operations with
             * man-in-the-middle protection
             */
            case BluetoothGattDescriptor.PERMISSION_WRITE_SIGNED_MITM:
                DescPermission = "BluetoothGattDescriptor.PERMISSION_WRITE_SIGNED_MITM: Descriptor permission: Allow signed write operations with man-in-the-middle protection";
                break;
            default :
                break;
        }

        return DescPermission;
    }
}

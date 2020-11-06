package com.my.device_info.work;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class Handle {

    private DeviceUtil deviceInfoModel = DeviceUtil.getInstance();

    public String get_androidID(Activity activity) {
        //Android 10 以后已经没有imei和meid了，取而代之的是Android Id
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            Toast.makeText(activity, "Android 10 后使用AndroidID", Toast.LENGTH_SHORT).show();
        }
        //检查是否有读取识别码的权限
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PERMISSION_GRANTED) {
            //如果没有就申请权限
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 10);
            return null;
        }
        return deviceInfoModel.getAndroidID(activity);
    }

    public String get_imei(Activity activity) {
        //Android 10 以后已经没有imei和meid了，取而代之的是Android Id
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Toast.makeText(activity, "Android 10 后使用 AndroidID", Toast.LENGTH_SHORT).show();
            return null;
        }

        //检查是否有读取识别码的权限
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PERMISSION_GRANTED) {
            //如果没有就申请权限
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 10);
            return null;
        }
        return deviceInfoModel.getIMEI(activity);
    }

    public String get_meid(Activity activity) {
        //Android 10 以后已经没有imei和meid了，取而代之的是Android Id
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Toast.makeText(activity, "Android 10 后使用 AndroidID", Toast.LENGTH_SHORT).show();
            return null;
        }

        //检查是否有读取识别码的权限
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PERMISSION_GRANTED) {
            //如果没有就申请权限
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 10);
            return null;
        }
        return deviceInfoModel.getMEID(activity);
    }

    //获取品牌
    public String getPhoneBrand(Activity activity) {
        return deviceInfoModel.getPhoneBrand();
    }

    //获取型号
    public String getPhoneMODEL(Activity activity) {
        return deviceInfoModel.getPhoneMODEL();
    }

    //获取手机分辨率
    public String getResolution(Activity activity) {
        return deviceInfoModel.getResolution(activity);
    }

    //获取运营商
    public String getNetOperator(Activity activity) {
        return deviceInfoModel.getNetOperator(activity);
    }

    //获取联网方式
    public String getNetMode(Activity activity) {
        return deviceInfoModel.getNetMode(activity);
    }

    //获取操作系统
    public String getOS(Activity activity) {
        return deviceInfoModel.getOS();
    }

    //获取wifi当前ip地址
    public String getLocalIpAddress(Activity activity) {
        return deviceInfoModel.getWifiIpAddress(activity);
    }

    //GPRS连接下的ip
    public String getLocalIpAddress_GPS(Activity activity) {
        return deviceInfoModel.getLocalIpAddress();
    }

    //获取序列号
    public String getSerialNumber(Activity activity) {
        return deviceInfoModel.getSerialNumber();
    }
}

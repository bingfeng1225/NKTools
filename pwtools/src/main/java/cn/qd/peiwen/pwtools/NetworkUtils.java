package cn.qd.peiwen.pwtools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    public static final int NET_WORK_NONE = 0;//无网络
    public static final int NET_WORK_WLAN = 1;//数据网络
    public static final int NET_WORK_WIFI = 2;//Wifi网络

    /**
     * 判断是否有网络
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (EmptyUtils.isEmpty(connectivity)) {
            return false;
        }
        NetworkInfo[] networkInfos = connectivity.getAllNetworkInfo();
        if (EmptyUtils.isEmpty(networkInfos)) {
            return false;
        }
        for (NetworkInfo networkInfo:networkInfos) {
            if (networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @return
     * @throws Exception
     */
    public static boolean isMobileDataEnable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (EmptyUtils.isEmpty(connectivity)) {
            return false;
        }
        NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(EmptyUtils.isEmpty(info)) {
            return false;
        }
        return info.isConnected();
    }


    /**
     * 判断wifi 是否可用
     *
     * @return
     * @throws Exception
     */
    public static boolean isWifiDataEnable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (EmptyUtils.isEmpty(connectivity)) {
            return false;
        }
        NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(EmptyUtils.isEmpty(info)) {
            return false;
        }
        return info.isConnected();
    }


    public static int getNetworkType(Context context) {
        if(isWifiDataEnable(context)){
            return NET_WORK_WIFI;
        }

        if(isMobileDataEnable(context)) {
            return NET_WORK_WLAN;
        }

        return NET_WORK_NONE;
    }

    private static String getMacAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface element = interfaces.nextElement();
                if ("wlan0".equals(element.getName())) {
                    byte[] macBytes = element.getHardwareAddress();
                    return ByteUtils.bytes2HexString(macBytes, false, ":");
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

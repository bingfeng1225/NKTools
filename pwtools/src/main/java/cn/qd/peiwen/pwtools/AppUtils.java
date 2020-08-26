package cn.qd.peiwen.pwtools;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by jeffreyliu on 17/1/16.
 */

public class AppUtils {
    /**
     * 获取PackageManager
     **/
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取PackageManager
     **/
    public static PackageManager getPackageManager(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager;
    }

    /**
     * 获取单个App的Package信息
     **/
    public static PackageInfo getPackageInfo(Context context, String packageName) {
        PackageManager packageManager = getPackageManager(context);
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    /**
     * 获取单个App的Application信息
     **/
    public static ApplicationInfo getApplicationInfo(Context context, String packageName) {
        PackageManager packageManager = getPackageManager(context);
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return applicationInfo;
    }

    /**
     * 获取单个App图标
     **/
    public static Drawable getAppIcon(Context context, String packageName) {
        PackageManager packageManager = getPackageManager(context);
        Drawable icon = null;
        try {
            icon = packageManager.getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return icon;
    }

    /**
     * 获取单个App名称
     **/
    public static String getAppName(Context context, String packageName) {
        PackageManager packageManager = getPackageManager(context);
        if (null == packageName) {
            packageName = getPackageName(context);
        }
        ApplicationInfo applicationInfo = getApplicationInfo(context, packageName);
        if (null != applicationInfo) {
            String appName = packageManager.getApplicationLabel(applicationInfo).toString();
            return appName;
        } else {
            return null;
        }
    }

    /**
     * 获取单个App版本号Name
     **/
    public static String getAppVersionName(Context context, String packageName) {
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (null != packageInfo) {
            return packageInfo.versionName;
        } else {
            return null;
        }
    }

    /**
     * 获取单个App版本号Code
     **/
    public static int getAppVersionCode(Context context, String packageName) {
        PackageInfo packageInfo = getPackageInfo(context, packageName);
        if (null != packageInfo) {
            return packageInfo.versionCode;
        } else {
            return -1;
        }
    }

    /**
     * 应用静默安装
     **/
    public static String[] silentInstall(String apkPath) {
        Process process = null;
        BufferedReader errorResult = null;
        BufferedReader successResult = null;
        StringBuilder errorMsg = new StringBuilder();
        StringBuilder successMsg = new StringBuilder();
        try {
            process = new ProcessBuilder("pm", "install", "-r", apkPath).start();
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (successResult != null) {
                    successResult.close();
                }
            } catch (Exception e) {

            }
            try {
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {

            }
            if (process != null) {
                process.destroy();
            }
        }
        return new String[]{
                errorMsg.toString(),
                successMsg.toString()
        };
    }
}

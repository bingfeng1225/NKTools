package cn.qd.peiwen.pwtools.brightness;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import java.lang.ref.WeakReference;

import cn.qd.peiwen.pwtools.EmptyUtils;

public class BrightnessManager {
    private Context context;
    private WeakReference<IBrightnessListener> listener;

    public BrightnessManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public void init() {
        this.registBrightnessObserver();
    }

    public void release() {
        this.listener = null;
        this.unregistBrightnessObserver();
    }

    public void changeListener(IBrightnessListener listener) {
        this.listener = new WeakReference<>(listener);
    }
    /**
     * 获得当前屏幕亮度值
     *
     * @return0--255
     */
    public int getBrightness() {
        int brightness = -1;
        try {
            brightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return brightness;
    }

    /**
     * 保存当前的屏幕亮度值，并使之生效
     *
     * @paramparamInt0-255
     */
    public void setBrightness(int brightness) {
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
            Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
            context.getContentResolver().notifyChange(uri, null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获得当前屏幕亮度的模式
     *
     * @return1为自动调节屏幕亮度,0为手动调节屏幕亮度,-1获取失败
     */
    public int getBrightnessMode() {
        int mode = -1;
        try {
            mode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return mode;
    }

    /**
     * 设置当前屏幕亮度的模式
     *
     * @parammode1为自动调节屏幕亮度,0为手动调节屏幕亮度
     */
    public void setBrightnessMode(int mode) {
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, mode);
            Uri uri = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS_MODE);
            context.getContentResolver().notifyChange(uri, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void registBrightnessObserver() {
        Uri brightness = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS_MODE);
        Uri brightnessModel = Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS_MODE);
        context.getContentResolver().registerContentObserver(brightness, false, brightnessObserver);
        context.getContentResolver().registerContentObserver(brightnessModel, false, brightnessModeObserver);
    }

    private void unregistBrightnessObserver() {
        context.getContentResolver().unregisterContentObserver(brightnessObserver);
        context.getContentResolver().unregisterContentObserver(brightnessModeObserver);
    }

    private ContentObserver brightnessObserver = new ContentObserver(null) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.e("","self ==== " + selfChange);
            if(EmptyUtils.isNotEmpty(listener)){
                listener.get().onBrightnessChanged();
            }
        }
    };

    private ContentObserver brightnessModeObserver = new ContentObserver(null) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            if(EmptyUtils.isNotEmpty(listener)){
                listener.get().onBrightnessModeChanged();
            }
        }
    };
}

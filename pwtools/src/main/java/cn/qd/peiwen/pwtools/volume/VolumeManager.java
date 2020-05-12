package cn.qd.peiwen.pwtools.volume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

import java.lang.ref.WeakReference;

import cn.qd.peiwen.pwtools.EmptyUtils;

/**
 * Created by jeffreyliu on 16/12/8.
 */

public class VolumeManager implements AudioManager.OnAudioFocusChangeListener {
    private int maxVolume = 0;
    private Context context = null;
    private AudioManager audioManager = null;
    private WeakReference<IVolumeListener> listener;

    public VolumeManager(Context context) {
        this.context = context.getApplicationContext();
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void init() {
        this.maxVolume = this.getMaxVolume();
        this.registerVolumeListener();
        this.registerAudioFocusListener();
    }

    public void release() {
        this.listener = null;
        this.unregisterVolumeListener();
        this.unregisterAudioFocusListener();
    }


    public void changeListener(IVolumeListener listener) {
        this.listener = new WeakReference<>(listener);
    }

    public int getVolume() {
        return this.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public int getMaxVolume() {
        return this.audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    public void setVolume(int volume) {
        if (volume < 0) {
            volume = 0;
        } else if (volume > maxVolume) {
            volume = maxVolume;
        }
        this.audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }

    public void adjustVolume(int volume) {
        int current = getVolume();
        int adjust = current + volume;
        if (adjust < 0) {
            adjust = 0;
        } else if (adjust > maxVolume) {
            adjust = maxVolume;
        }
        this.audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, adjust, 0);
    }

    private void registerVolumeListener() {
        IntentFilter filter = new IntentFilter("android.media.VOLUME_CHANGED_ACTION");
        context.registerReceiver(this.volumeBroadcastReceiver, filter);
    }

    private void unregisterVolumeListener() {
        context.unregisterReceiver(this.volumeBroadcastReceiver);
    }

    private void registerAudioFocusListener() {
        audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }

    private void unregisterAudioFocusListener() {
        audioManager.abandonAudioFocus(this);
    }

    private void fireVolumeChanged() {
        if (EmptyUtils.isNotEmpty(this.listener)) {
            this.listener.get().onVolumeChanged(getVolume());
        }
    }

    private void fireAudioFocusLossed() {
        if (EmptyUtils.isNotEmpty(this.listener)) {
            this.listener.get().onAudioFocusLossed();
        }
    }

    private void fireAudioFocusGranted() {
        if (EmptyUtils.isNotEmpty(this.listener)) {
            this.listener.get().onAudioFocusGranted();
        }
    }

    @Override
    public void onAudioFocusChange(int focus) {
        switch (focus) {
            case AudioManager.AUDIOFOCUS_GAIN:
                fireAudioFocusGranted();
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                fireAudioFocusLossed();
                break;
        }
    }

    private BroadcastReceiver volumeBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.media.VOLUME_CHANGED_ACTION".equals(action)) {
                if (AudioManager.STREAM_MUSIC == intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", AudioManager.STREAM_MUSIC)) {
                    fireVolumeChanged();
                }
            }
        }
    };

}

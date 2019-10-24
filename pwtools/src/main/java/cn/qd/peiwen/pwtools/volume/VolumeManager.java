package cn.qd.peiwen.pwtools.volume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

import java.lang.ref.WeakReference;

/**
 * Created by jeffreyliu on 16/12/8.
 */

public class VolumeManager implements AudioManager.OnAudioFocusChangeListener {
    private int volume = 0;
    private int maxVolume = 0;
    private Context context = null;
    private boolean isSetMute = false;
    private AudioManager audioManager = null;
    private WeakReference<IVolumeListener> listener;

    public VolumeManager(Context context) {
        this.context = context.getApplicationContext();
        this.audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void init() {
        this.maxVolume = this.getMaxVolume();
        this.volume = getVolume();
        this.registerVolumeListener();
        this.registerAudioFocusListener();
    }


    public boolean isMuted() {
        return (this.volume == 0);
    }

    public void setMute(boolean mute) {
        if (mute != isMuted()) {
            isSetMute = true;
            if (mute) {
                this.setVolume(0);
            } else {
                this.setVolume(30);
            }
        } else {
            fireMuteChanged();
        }
    }

    public void setVolume(int volume) {
        if (volume != this.volume) {
            int temp = Math.round(volume * maxVolume / 100.f);
            this.audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, temp, 0);
        } else {
            fireVolumeChanged();
        }
    }

    public void adjustVolume(int volume) {
        int adjust = this.volume + volume;
        if (adjust < 0) {
            adjust = 0;
        }
        if (adjust > 100) {
            adjust = 100;
        }
        if (adjust != this.volume) {
            int temp = Math.round(adjust * maxVolume / 100.f);
            this.audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, temp, 0);
        } else {
            fireVolumeChanged();
        }
    }

    public int getVolume() {
        int volume = this.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        return Math.round(volume * 100.f / maxVolume);
    }

    public int getMaxVolume() {
        return this.audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    public void setListener(IVolumeListener listener) {
        this.listener = new WeakReference<>(listener);
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

    private void fireMuteChanged() {
        if (null != this.listener && null != this.listener.get()) {
            this.listener.get().onMuteChanged(getVolume());
        }
    }

    private void fireVolumeChanged() {
        if (null != this.listener && null != this.listener.get()) {
            this.listener.get().onVolumeChanged(getVolume());
        }
    }

    private void fireAudioFocusLossed() {
        if (null != this.listener && null != this.listener.get()) {
            this.listener.get().onAudioFocusLossed();
        }
    }

    private void fireAudioFocusGranted() {
        if (null != this.listener && null != this.listener.get()) {
            this.listener.get().onAudioFocusGranted();
        }
    }

    public void release() {
        this.listener = null;
        this.unregisterVolumeListener();
        this.unregisterAudioFocusListener();
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
                    int temp = getVolume();
                    if (volume != temp) {
                        volume = temp;
                        if (isSetMute) {
                            isSetMute = false;
                            fireMuteChanged();
                        } else {
                            fireVolumeChanged();
                        }
                    }
                }
            }
        }
    };

}

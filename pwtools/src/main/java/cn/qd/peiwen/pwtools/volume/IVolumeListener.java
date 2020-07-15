package cn.qd.peiwen.pwtools.volume;

/**
 * Created by nick on 2017/12/15.
 */

public interface IVolumeListener {
    void onAudioFocusLossed();

    void onAudioFocusGranted();

    void onVolumeChanged(int volume);
}

package cn.qd.peiwen.demo;

import android.os.Bundle;
import android.util.Log;

import java.nio.ByteOrder;

import androidx.appcompat.app.AppCompatActivity;
import cn.qd.peiwen.pwtools.ByteUtils;
import cn.qd.peiwen.pwtools.brightness.BrightnessManager;
import cn.qd.peiwen.pwtools.volume.VolumeManager;

public class MainActivity extends AppCompatActivity {

    private VolumeManager volume;
    private BrightnessManager brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        byte[] data = new byte[]{0x0E, 0x06, 0x00, 0x08, 0x04, 0x00, 0x08, 0x04, 0x26, (byte) 0xAE, 0x30, 0x2E, 0x61, 0x03};
        byte xor = ByteUtils.computeXORCode(data, 0, data.length - 2);
        byte xorInver = ByteUtils.computeXORInverse(data, 0, data.length - 2);

        byte[] buf = new byte[]{xor, xorInver, data[data.length - 2]};
        Log.e("测试",ByteUtils.bytes2HexString(buf,true,", "));

        long value = 774926202;
        buf = ByteUtils.long2Bytes(value);
        Log.e("测试",ByteUtils.bytes2HexString(buf,true,", "));
        value = ByteUtils.bytes2Long(buf);
        Log.e("测试","" + value);
        buf = ByteUtils.long2BytesLE(value);
        Log.e("测试",ByteUtils.bytes2HexString(buf,true,", "));
        value = ByteUtils.bytes2Long(buf, ByteOrder.LITTLE_ENDIAN);
        Log.e("测试","" + value);

        buf = new byte[] {0x01, 0x10, 0x40, 0x1F, 0x32, 0x00, (byte)0xF1, 0x6F};
        byte[] crc = ByteUtils.computeCRC16Code(buf,0,6);
        Log.e("测试","crc = " + ByteUtils.bytes2HexString(crc));
        byte[] crcLE = ByteUtils.computeCRC16CodeLE(buf,0,6);
        Log.e("测试","crcLE = " + ByteUtils.bytes2HexString(crcLE));

        buf = new byte[10000];
        for (int i = 0; i< 10000;i++){
            buf[i] = (byte)0xFF;
        }
        byte sumL8 = ByteUtils.computeL8SumCode(buf);
        Log.e("测试","sumL8 = " + sumL8);

        volume = new VolumeManager(this);
        brightness = new BrightnessManager(this);

        volume.init();
        brightness.init();


        volume.setVolume(70);

        int mode = brightness.getBrightnessMode();
        if(mode != 1){
            brightness.setBrightnessMode(1);
        }

        brightness.setBrightness(120);
    }
}

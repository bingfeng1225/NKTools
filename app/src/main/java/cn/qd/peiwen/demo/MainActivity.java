package cn.qd.peiwen.demo;

import android.os.Bundle;
import android.util.Log;

import java.nio.ByteOrder;

import androidx.appcompat.app.AppCompatActivity;
import cn.qd.peiwen.pwtools.ByteUtils;

public class MainActivity extends AppCompatActivity {

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
        byte[] crc = ByteUtils.computeCRCCode(buf,0,6);
        Log.e("测试","crc = " + ByteUtils.bytes2HexString(crc));
    }
}

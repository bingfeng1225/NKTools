package cn.qd.peiwen.demo;

import androidx.appcompat.app.AppCompatActivity;
import cn.qd.peiwen.demo.R;
import cn.qd.peiwen.pwtools.ByteUtils;

import android.os.Bundle;
import android.util.Log;

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
    }
}

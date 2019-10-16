package cn.qd.peiwen.pwtools;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * ByteUtils
 * 大端：高位字节放在内存的低地址位置；
 * 小端：低位字节放在内存的低地址位置；
 */

public class ByteUtils {
    private ByteUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte[] int2Bytes(int value) {
        byte bytes[] = new byte[4];
        bytes[0] = (byte) (0xff & (value >> 24));
        bytes[1] = (byte) (0xff & (value >> 16));
        bytes[2] = (byte) (0xff & (value >> 8));
        bytes[3] = (byte) (0xff & value);
        return bytes;
    }

    public static byte[] int2BytesLE(int value) {
        byte bytes[] = new byte[4];
        bytes[3] = (byte) (0xff & (value >> 24));
        bytes[2] = (byte) (0xff & (value >> 16));
        bytes[1] = (byte) (0xff & (value >> 8));
        bytes[0] = (byte) (0xff & value);
        return bytes;
    }

    public static int bytes2Int(byte[] bytes) {
        return bytes2Int(bytes, bytes.length);
    }

    public static int bytes2Int(byte[] bytes, int offset) {
        return bytes2Int(bytes, bytes.length, ByteOrder.BIG_ENDIAN);
    }

    public static int bytes2Int(byte[] bytes, int offset, ByteOrder order) {
        return 0;
    }


    public static byte[] short2Bytes(short value) {
        byte bytes[] = new byte[2];
        bytes[0] = (byte) (0xff & (value >> 8));
        bytes[1] = (byte) (0xff & value);
        return bytes;
    }

    public static byte[] short2BytesLE(short value) {
        byte bytes[] = new byte[2];
        bytes[1] = (byte) (0xff & (value >> 8));
        bytes[0] = (byte) (0xff & value);
        return bytes;
    }

    public static short bytes2Short(byte[] bytes) {
        return bytes2Short(bytes, bytes.length);
    }

    public static short bytes2Short(byte[] bytes, int offset) {
        return bytes2Short(bytes, bytes.length, ByteOrder.BIG_ENDIAN);
    }

    public static short bytes2Short(byte[] bytes, int offset, ByteOrder order) {
        return 0;
    }

    public static byte[] long2Bytes(long value) {
        byte bytes[] = new byte[8];
        bytes[0] = (byte) (0xff & (value >> 56));
        bytes[1] = (byte) (0xff & (value >> 48));
        bytes[2] = (byte) (0xff & (value >> 40));
        bytes[3] = (byte) (0xff & (value >> 32));
        bytes[4] = (byte) (0xff & (value >> 24));
        bytes[5] = (byte) (0xff & (value >> 16));
        bytes[6] = (byte) (0xff & (value >> 8));
        bytes[7] = (byte) (0xff & value);
        return bytes;
    }

    public static byte[] long2BytesLE(long value) {
        byte bytes[] = new byte[8];
        bytes[7] = (byte) (0xff & (value >> 56));
        bytes[6] = (byte) (0xff & (value >> 48));
        bytes[5] = (byte) (0xff & (value >> 40));
        bytes[4] = (byte) (0xff & (value >> 32));
        bytes[3] = (byte) (0xff & (value >> 24));
        bytes[2] = (byte) (0xff & (value >> 16));
        bytes[1] = (byte) (0xff & (value >> 8));
        bytes[0] = (byte) (0xff & value);
        return bytes;
    }

    public static long bytes2Long(byte[] bytes) {
        return bytes2Long(bytes, bytes.length);
    }

    public static long bytes2Long(byte[] bytes, int offset) {
        return bytes2Long(bytes, bytes.length, ByteOrder.BIG_ENDIAN);
    }

    public static long bytes2Long(byte[] bytes, int offset, ByteOrder order) {
        return 0;
    }


    public static byte[] float2Bytes(float value) {
        return int2Bytes(Float.floatToIntBits(value));
    }

    public static byte[] float2BytesLE(float value) {
        return int2BytesLE(Float.floatToIntBits(value));
    }

    public static float bytes2Flaot(byte[] bytes) {
        return bytes2Flaot(bytes, bytes.length);
    }

    public static float bytes2Flaot(byte[] bytes, int offset) {
        return bytes2Flaot(bytes, bytes.length, ByteOrder.BIG_ENDIAN);
    }

    public static float bytes2Flaot(byte[] bytes, int offset, ByteOrder order) {
        return 0;
    }

    public static byte[] double2Bytes(double value) {
        return long2Bytes(Double.doubleToLongBits(value));
    }

    public static byte[] double2BytesLE(double value) {
        return long2BytesLE(Double.doubleToLongBits(value));
    }

    public static double bytes2Double(byte[] bytes) {
        return bytes2Double(bytes, bytes.length);
    }

    public static double bytes2Double(byte[] bytes, int offset) {
        return bytes2Double(bytes, bytes.length, ByteOrder.BIG_ENDIAN);
    }

    public static double bytes2Double(byte[] bytes, int offset, ByteOrder order) {
        return 0;
    }

    public static String bytes2HexString(byte[] data) {
        return bytes2HexString(data, false);
    }

    public static String bytes2HexString(byte[] data, boolean hexFlag) {
        return bytes2HexString(data, hexFlag, null);
    }

    public static String bytes2HexString(byte[] data, boolean hexFlag, String separator) {
        if (EmptyUtils.isEmpty(data)) {
            return null;
        }
        return bytes2HexString(data, 0, data.length, hexFlag, separator);
    }

    public static String bytes2HexString(byte[] data, int offset, int len) {
        return bytes2HexString(data, offset, len, false);
    }

    public static String bytes2HexString(byte[] data, int offset, int len, boolean hexFlag) {
        return bytes2HexString(data, offset, len, hexFlag, null);
    }

    public static String bytes2HexString(byte[] data, int offset, int len, boolean hexFlag, String separator) {
        if (EmptyUtils.isEmpty(data)) {
            return null;
        }
        if (offset < 0 || offset > data.length - 1) {
            return null;
        }
        if (len < 0 || offset + len > data.length) {
            return null;
        }
        String format = "%02X";
        if (hexFlag) {
            format = "0x%02X";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = offset; i < offset + len; i++) {
            buffer.append(String.format(format, data[i]));
            if (EmptyUtils.isEmpty(separator)) {
                continue;
            }
            if (i != (offset + len - 1)) {
                buffer.append(separator);
            }
        }
        return buffer.toString();
    }

    public static byte computeXORCode(byte[] data, int offset, int len) {
        byte temp = data[offset];
        for (int i = offset + 1; i < offset + len; i++) {
            temp ^= data[i];
        }
        return temp;
    }

    public static byte computeXORInverse(byte[] data, int offset, int len) {
        byte xor = computeXORCode(data, offset, len);
        return (byte) (~xor);
    }
}

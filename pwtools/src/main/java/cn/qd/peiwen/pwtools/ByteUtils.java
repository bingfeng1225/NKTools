package cn.qd.peiwen.pwtools;

public class ByteUtils {
    private ByteUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static byte computeXORCode(byte[] data, int offset, int len) {
        byte temp = data[offset];
        for (int i = offset + 1; i < offset + len; i++) {
            temp ^= data[i];
        }
        return temp;
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        return toHexString(data, 0, data.length);
    }

    public static String toHexString(byte[] data, int offset, int len) {
        if (data == null) {
            return null;
        }
        if (offset < 0 || offset > data.length - 1) {
            return null;
        }
        if (len < 0 || offset + len > data.length) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = offset; i < offset + len; i++) {
            buffer.append(String.format("0x%02X ", data[i]));
        }
        return buffer.toString();
    }
}

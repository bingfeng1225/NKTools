package cn.qd.peiwen.pwtools;

public class ByteUtils {
    private ByteUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String toHexString(byte[] data) {
        return toHexString(data, false);
    }

    public static String toHexString(byte[] data, boolean hexFlag) {
        return toHexString(data, hexFlag, null);
    }

    public static String toHexString(byte[] data, boolean hexFlag, String separator) {
        if (EmptyUtils.isEmpty(data)) {
            return null;
        }
        return toHexString(data, 0, data.length, hexFlag, separator);
    }

    public static String toHexString(byte[] data, int offset, int len) {
        return toHexString(data, offset, len, false);
    }

    public static String toHexString(byte[] data, int offset, int len, boolean hexFlag) {
        return toHexString(data, offset, len, hexFlag, null);
    }

    public static String toHexString(byte[] data, int offset, int len, boolean hexFlag, String separator) {
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

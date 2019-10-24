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
        return bytes2Int(bytes, 0);
    }

    public static int bytes2Int(byte[] bytes, int offset) {
        return bytes2Int(bytes, offset, ByteOrder.BIG_ENDIAN);
    }

    public static int bytes2Int(byte[] bytes, ByteOrder order) {
        return bytes2Int(bytes, 0, order);
    }

    public static int bytes2Int(byte[] bytes, int offset, ByteOrder order) {
        if (offset < 0 || offset > bytes.length - 1) {
            throw new IllegalArgumentException("The offset index out of bounds");
        }
        if (bytes.length - offset < 4) {
            throw new IllegalArgumentException("The bytes (legth - offset) < int bytes(4)");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, 4);
        if (order == ByteOrder.LITTLE_ENDIAN) {
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getInt();
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
        return bytes2Short(bytes, 0);
    }

    public static short bytes2Short(byte[] bytes, int offset) {
        return bytes2Short(bytes, offset, ByteOrder.BIG_ENDIAN);
    }

    public static short bytes2Short(byte[] bytes, ByteOrder order) {
        return bytes2Short(bytes, 0, order);
    }

    public static short bytes2Short(byte[] bytes, int offset, ByteOrder order) {
        if (offset < 0 || offset > bytes.length - 1) {
            throw new IllegalArgumentException("The offset index out of bounds");
        }
        if (bytes.length - offset < 2) {
            throw new IllegalArgumentException("The bytes (legth - offset) < short bytes(2)");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, 2);
        if (order == ByteOrder.LITTLE_ENDIAN) {
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getShort();
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
        return bytes2Long(bytes, 0);
    }

    public static long bytes2Long(byte[] bytes, int offset) {
        return bytes2Long(bytes, offset, ByteOrder.BIG_ENDIAN);
    }

    public static long bytes2Long(byte[] bytes, ByteOrder order) {
        return bytes2Long(bytes, 0, order);
    }

    public static long bytes2Long(byte[] bytes, int offset, ByteOrder order) {
        if (offset < 0 || offset > bytes.length - 1) {
            throw new IllegalArgumentException("The offset index out of bounds");
        }
        if (bytes.length - offset < 8) {
            throw new IllegalArgumentException("The bytes (legth - offset) < long bytes(8)");
        }
        ByteBuffer buffer = ByteBuffer.wrap(bytes, offset, 8);
        if (order == ByteOrder.LITTLE_ENDIAN) {
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getLong();
    }


    public static byte[] float2Bytes(float value) {
        return int2Bytes(Float.floatToIntBits(value));
    }

    public static byte[] float2BytesLE(float value) {
        return int2BytesLE(Float.floatToIntBits(value));
    }

    public static float bytes2Flaot(byte[] bytes) {
        return bytes2Flaot(bytes, 0);
    }

    public static float bytes2Flaot(byte[] bytes, int offset) {
        return bytes2Flaot(bytes, offset, ByteOrder.BIG_ENDIAN);
    }

    public static float bytes2Flaot(byte[] bytes, ByteOrder order) {
        return bytes2Flaot(bytes, 0, order);
    }

    public static float bytes2Flaot(byte[] bytes, int offset, ByteOrder order) {
        int temp = bytes2Int(bytes, offset, order);
        return Float.intBitsToFloat(temp);
    }

    public static byte[] double2Bytes(double value) {
        return long2Bytes(Double.doubleToLongBits(value));
    }

    public static byte[] double2BytesLE(double value) {
        return long2BytesLE(Double.doubleToLongBits(value));
    }

    public static double bytes2Double(byte[] bytes) {
        return bytes2Double(bytes, 0);
    }

    public static double bytes2Double(byte[] bytes, int offset) {
        return bytes2Double(bytes, offset, ByteOrder.BIG_ENDIAN);
    }

    public static double bytes2Double(byte[] bytes, ByteOrder order) {
        return bytes2Double(bytes, 0, order);
    }

    public static double bytes2Double(byte[] bytes, int offset, ByteOrder order) {
        long temp = bytes2Long(bytes, offset, order);
        return Double.longBitsToDouble(temp);
    }

    public static String bytes2HexString(byte[] data) {
        return bytes2HexString(data, false);
    }

    public static String bytes2HexString(byte[] data, boolean hexFlag) {
        return bytes2HexString(data, hexFlag, null);
    }

    public static String bytes2HexString(byte[] data, boolean hexFlag, String separator) {
        if (EmptyUtils.isEmpty(data)) {
            throw new IllegalArgumentException("The data can not be blank");
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
            throw new IllegalArgumentException("The data can not be blank");
        }
        if (offset < 0 || offset > data.length - 1) {
            throw new IllegalArgumentException("The offset index out of bounds");
        }
        if (len < 0 || offset + len > data.length) {
            throw new IllegalArgumentException("The len can not be < 0 or (offset + len) index out of bounds");
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

    public static byte computeXORCode(byte[] data) {
        if (EmptyUtils.isEmpty(data)) {
            throw new IllegalArgumentException("The data can not be blank");
        }
        return computeXORCode(data, 0, data.length);
    }

    public static byte computeXORCode(byte[] data, int offset, int len) {
        if (EmptyUtils.isEmpty(data)) {
            throw new IllegalArgumentException("The data can not be blank");
        }
        if (offset < 0 || offset > data.length - 1) {
            throw new IllegalArgumentException("The offset index out of bounds");
        }
        if (len < 0 || offset + len > data.length) {
            throw new IllegalArgumentException("The len can not be < 0 or (offset + len) index out of bounds");
        }
        byte temp = data[offset];
        for (int i = offset + 1; i < offset + len; i++) {
            temp ^= data[i];
        }
        return temp;
    }

    public static byte computeXORInverse(byte[] data) {
        if (EmptyUtils.isEmpty(data)) {
            throw new IllegalArgumentException("The data can not be blank");
        }
        return computeXORInverse(data, 0, data.length);
    }

    public static byte computeXORInverse(byte[] data, int offset, int len) {
        byte xor = computeXORCode(data, offset, len);
        return (byte) (~xor);
    }


    public static byte[] computeCRCCode(byte[] data) {
        if (EmptyUtils.isEmpty(data)) {
            throw new IllegalArgumentException("The data can not be blank");
        }
        return computeCRCCode(data, 0, data.length);
    }

    public static byte[] computeCRCCode(byte[] data, int offset, int len) {
        if (EmptyUtils.isEmpty(data)) {
            throw new IllegalArgumentException("The data can not be blank");
        }
        if (offset < 0 || offset > data.length - 1) {
            throw new IllegalArgumentException("The offset index out of bounds");
        }
        if (len < 0 || offset + len > data.length) {
            throw new IllegalArgumentException("The len can not be < 0 or (offset + len) index out of bounds");
        }
        int crc = 0xFFFF;
        for (int pos = offset; pos < offset + len; pos++) {
            if (data[pos] < 0) {
                crc ^= (int) data[pos] + 256; // XOR byte into least sig. byte of
                // crc
            } else {
                crc ^= (int) data[pos]; // XOR byte into least sig. byte of crc
            }
            for (int i = 8; i != 0; i--) { // Loop over each bit
                if ((crc & 0x0001) != 0) { // If the LSB is set
                    crc >>= 1; // Shift right and XOR 0xA001
                    crc ^= 0xA001;
                } else {
                    // Else LSB is not set
                    crc >>= 1; // Just shift right
                }
            }
        }
        String c = Integer.toHexString(crc).toUpperCase();
        if (c.length() == 4) {
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 3) {
            c = "0" + c;
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 2) {
            c = "0" + c.substring(1, 2) + "0" + c.substring(0, 1);
        }

        return new byte[]{
                (byte) (Integer.parseInt(c.substring(0, 2), 16)),
                (byte) (Integer.parseInt(c.substring(2, 4), 16))
        };
    }
}

package cn.qd.peiwen.pwtools;

public class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    public static boolean isEquals(String str1, String str2) {
        if (EmptyUtils.isEmpty(str1)) {
            if (EmptyUtils.isEmpty(str2)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (EmptyUtils.isEmpty(str2)) {
                return false;
            } else {
                return str1.equals(str2);
            }
        }
    }
}

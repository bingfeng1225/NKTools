package cn.qd.peiwen.pwtools;

public class ThreadUtils {
    private ThreadUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package cn.qd.peiwen.pwtools.logger;

import org.apache.log4j.Logger;

/**
 * Log工具，类似android.util.Log。
 * tag自动产生
 */
public class PWLogger {
    private static Logger logger = Logger.getRootLogger();

    private static String filtrateInnerClass(String className) {
        if (className.contains("$")) {
            return className.substring(0, className.indexOf('$'));
        }
        return className;
    }

    private static String generateTag(StackTraceElement caller) {
        String tag = "(%s.java:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, filtrateInnerClass(callerClazzName), caller.getLineNumber());
        return tag;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void d(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.debug(tag + "->" + content);
    }

    public static void d(Throwable throwable) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.debug(tag, throwable);
    }

    public static void e(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.error(tag + "->" + content);
    }

    public static void e(Throwable throwable) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.error(tag, throwable);
    }

    public static void f(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.fatal(tag + "->" + content);
    }

    public static void f(Throwable throwable) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.fatal(tag, throwable);
    }

    public static void i(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.info(tag + "->" + content);
    }

    public static void i(Throwable throwable) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.info(tag, throwable);
    }

    public static void w(String content) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.warn(tag + "->" + content);
    }

    public static void w(Throwable throwable) {
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        logger.warn(tag, throwable);
    }
}
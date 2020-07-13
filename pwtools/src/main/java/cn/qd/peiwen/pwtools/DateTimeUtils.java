package cn.qd.peiwen.pwtools;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateTimeUtils {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String[] zodiacArr = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    public static final String[] constellationArr = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    public static final int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static int yearByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static int monthByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static int weekByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static int dayByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static int hourByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static int minuteByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static int secondByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static int millisecondByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MILLISECOND);
    }

    /**
     * 根据日期获取生肖
     *
     * @return
     */
    public static String zodicaByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return zodiacArr[cal.get(Calendar.YEAR) % 12];
    }

    /**
     * 根据日期获取星座
     *
     * @return
     */
    public static String constellationByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        return constellationArr[11];
    }


    /***
     *获取于一个日期相差几天的某天的开始时间
     *
     * @param date      参照日期
     * @param offset    偏移日期
     * @return 对应天的开始日期
     */
    public static Date beginTimeOfDayByDate(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, offset);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /***
     *获取于一个日期相差几天的某天的结束时间
     *
     * @param date      参照日期
     * @param offset    偏移日期
     * @return 对应天的结束日期
     */
    public static Date endTimeOfDayByDate(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, offset);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }


    /***
     *获取于一个日期对应的周的开始时间
     *
     * @param date      参照日期
     * @return 对应周的开始日期，周日为第一天
     */
    public static Date beginTimeOfWeekByDate(Date date, int offset) {
        int weakday = weekByDate(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * offset - weakday + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /***
     *获取于一个日期对应的周的结束时间
     *
     * @param date      参照日期
     * @return 对应周的结束日期
     */
    public static Date endTimeOfWeekByDate(Date date, int offset) {
        int weakday = weekByDate(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 7 * offset + 7 - weakday);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /***
     *获取于一个日期对应的月的开始时间
     *
     * @param date      参照日期
     * @return 对应月的开始日期
     */
    public static Date beginTimeOfMonthByDate(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, offset);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /***
     *获取于一个日期对应的月的结束时间
     *
     * @param date      参照日期
     * @return 对应月的结束日期
     */
    public static Date endTimeOfMonthByDate(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, offset);
        int day = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /***
     *获取于一个日期对应的年的开始时间
     *
     * @param date      参照日期
     * @return 对应年的开始日期
     */
    public static Date beginTimeOfYearByDate(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, offset);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /***
     *获取于一个日期对应的年的结束时间
     *
     * @param date      参照日期
     * @return 对应年的结束日期
     */
    public static Date endTimeOfYearByDate(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, offset);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /***
     * 两个时间间的间隔
     * @param date1  第一个时间
     * @param date2  第二个时间
     * @return 两个时间间的间隔(毫秒)
     */
    public static long intervalBetweenDates(Date date1, Date date2) {
        return date1.getTime() - date2.getTime();
    }

    /**
     * 获取两个日期中的最大日期
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date maximumBetweenDates(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    /**
     * 获取两个日期中的最小日期
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date minimumBetweenDates(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    /**
     * 两个日期相差的天数(隔着一个24点就算是一天)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long differenceBetweenDates(Date date1, Date date2) {
        Date max = maximumBetweenDates(date1, date2);
        Date min = minimumBetweenDates(date1, date2);
        Date end = endTimeOfDayByDate(max, 0);
        Date start = beginTimeOfDayByDate(min, 0);
        long milliseconds = intervalBetweenDates(end, start);
        long millisecondsday = 24 * 60 * 60 * 1000;
        long days = milliseconds / millisecondsday;
        return days;
    }

    /**
     * 格式化日期
     * yyyy-MM-dd HH:mm:ss
     *
     * @param @param  date
     * @param @return
     * @Description:
     */
    public static Date format(String sdate) {
        return format(sdate, null);
    }

    public static Date format(String sdate, TimeZone timeZone) {
        return format(sdate, timeZone, DEFAULT_DATE_PATTERN);
    }

    public static Date format(String sdate, TimeZone timeZone, String pattern) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat(pattern);
            if (EmptyUtils.isNotEmpty(timeZone)) {
                sd.setTimeZone(timeZone);
            }
            return sd.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     * yyyy-MM-dd HH:mm:ss
     *
     * @param @param  date
     * @param @return
     * @Description:
     */
    public static String format(Date date) {
        return format(date, null);
    }

    public static String format(Date date, TimeZone timeZone) {
        return format(date, timeZone, DEFAULT_DATE_PATTERN);
    }

    public static String format(Date date, TimeZone timeZone, String pattern) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat(pattern);
            if (EmptyUtils.isNotEmpty(timeZone)) {
                sd.setTimeZone(timeZone);
            }
            return sd.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isAvailablePattern(String pattern) {
        if (EmptyUtils.isEmpty(pattern)) {
            return false;
        }
        String dateTime = DateTimeUtils.format(new Date(), null, pattern);
        return EmptyUtils.isNotEmpty(dateTime);
    }
}

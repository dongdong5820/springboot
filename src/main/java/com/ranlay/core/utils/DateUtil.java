package com.ranlay.core.utils;

import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author: Ranlay.su
 * @date: 2021-07-06 17:58
 * @version: 1.0
 */
public class DateUtil {

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param format 日期格式, 如 yyyy-MM-dd
     * @return
     */
    public static String timestampToDate(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param dateStr 字符串日期
     * @param format 格式
     * @return
     */
    public static Integer dateToTimestamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return Integer.valueOf((int) (sdf.parse(dateStr).getTime() / 1000));
        } catch (Exception ex) {
            return 0;
        }
    }

    /**
     * 获取当前时间戳(精确到秒)
     * @return
     */
    public static String timestamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /**
     * 获取当年的开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @return
     */
    public static Long getYearStartTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, 0);
        calendar.add(Calendar.DATE, 0);
        calendar.add(Calendar.MONTH, 0);
        return getYearStartTime(calendar);
    }

    /**
     * 获取某年的开始时间戳(毫秒级)
     * @param year 年
     * @return
     */
    public static Long getYearStartTime(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return getYearStartTime(calendar);
    }

    private static Long getYearStartTime(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当年的结束时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @return
     */
    public static Long getYearEndTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return getYearEndTime(calendar);
    }

    /**
     * 获取某年的结束时间戳(毫秒级)
     * @param year 年
     * @return
     */
    public static Long getYearEndTime(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return getYearEndTime(calendar);
    }

    private static Long getYearEndTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取前一年的时间
     * @param timeStamp
     * @return
     */
    public static Long getLastYearStartTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取多少天前的开始时间戳
     *
     * @param timeStamp 毫秒级时间戳
     * @return
     */
    public static Long queryLastDayStartTimestamp(Long timeStamp , int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }


    /**
     * 获取某天0点时间戳
     * @param timeStamp
     * @return
     */
    public static Long getTodayStartTime(Long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     * @param time  时间戳
     * @param formatStr 时间格式
     * @param timeZone 时区:不传时区为系统默认时区
     * @return
     */
    public static String convertTimeToString(Long time, String formatStr, String timeZone){
        Assert.notNull(time, "time is null");
        ZoneId zoneId = StringUtil.isEmpty(timeZone) ? ZoneId.systemDefault() : ZoneId.of(timeZone);
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(formatStr);
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), zoneId));
    }
}

package com.ranlay.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}

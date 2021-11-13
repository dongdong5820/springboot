package com.ranlay.core.utils;

/**
 * @author: Ranlay.su
 * @date: 2021-11-08 18:12
 * @description: 字符串工具类
 * @version: 1.0.0
 */
public class StringUtil {

    /**
     * 判断是否为null或空字符串
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if(o == null || "".equals(o)){
            return true;
        }
        if(o instanceof String){
            return ((String)o).trim().isEmpty();
        }else{
            return o.toString().trim().isEmpty();
        }
    }

    /**
     * 获取匹配字符（串）的索引值
     * @param str
     * @param value
     * @param fromIndex
     * @return
     */
    public static int indexOf(String str, String value, int fromIndex) {
        int i = str.indexOf(value, fromIndex);
        return i;
    }

    /**
     * 获取匹配字符（串）的索引值
     * @param str
     * @param value
     * @return
     */
    public static int indexOf(String str, String value) {
        return indexOf(str, value, 0);
    }

    /**
     * 最后一次出现的索引位置
     * @param str
     * @param value
     * @return
     */
    public static int lastIndexOf(String str, String value) {
        int i = str.lastIndexOf(value);
        return i;
    }

    /**
     * 截取字符串
     * @param str
     * @param beginIndex
     * @return
     */
    public static String substring(String str, int beginIndex) {
        String s = str.substring(beginIndex);
        return s;
    }

    /**
     * 从特定字符出现的最后一次位置截取
     * @param str
     * @param value
     * @return
     */
    public static String lastSubstring(String str, String value) {
        int i = lastIndexOf(str, value);
        return substring(str, i + 1);
    }
}

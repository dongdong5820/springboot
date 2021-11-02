package com.ranlay.core.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Ranlay.su
 * @date: 2021-09-14 15:35
 * @description: List工具类
 * @version: 1.0.0
 */
public class ListUtil {
    /**
     * 集合切割
     * @param list
     * @param perListNum
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> listChunk(List<T> list, int perListNum) {
        int step = (list.size() + perListNum - 1)/perListNum;
        List<List<T>> collect = Stream.iterate(0, n -> n + 1).limit(step).parallel().map(
                a -> list.stream().skip(a * perListNum).limit(perListNum).parallel().collect(Collectors.toList())
        ).collect(Collectors.toList());
        return collect;
    }

    /**
     * 集合元素去重
     * @param list
     */
    public static void removeDuplicate(List<?> list){
        for(int i = 0; i < list.size() - 1; i++){
            for(int j = list.size() - 1; j > i; j--){
                if(list.get(j).equals(list.get(i))){
                    list.remove(j);
                }
            }
        }
    }

    /**
     * 列表转换成字符串
     * @param list
     * @param separator
     * @return
     */
    public static String implode(List<String> list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i  = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }
}

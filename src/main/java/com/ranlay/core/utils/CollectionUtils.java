package com.ranlay.core.utils;

import java.util.*;

/**
 * 集合工具
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }

    /**
     * 按指定大小，分割List集合，将集合按指定大小拆开
     * 此方法是最快的
     *
     * @param list 指定需要拆分的集合
     * @param len  指定集合大小
     * @return 返回拆分后的各个集合
     */
    public static <T> List<List<T>> split(List<T> list, int len) {
        if (CollectionUtils.isEmpty(list) || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            // subList(start,stop) 包含start,不包含stop
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }


    /**
     * 按指定大小，分割List集合，将集合按指定大小拆开
     *
     * @param set 指定需要拆分的集合
     * @param len 指定集合大小
     * @return 返回拆分后的各个集合
     */
    public static <T> List<Set<T>> split(Set<T> set, int len) {
        if (CollectionUtils.isEmpty(set) || len < 1) {
            return null;
        }
        List<T> objects = new ArrayList<T>(set);
        List<List<T>> splitResult = CollectionUtils.split(objects, len);
        List<Set<T>> result = new ArrayList<>();
        for (List<T> subList : splitResult) {
            result.add(new HashSet<>(subList));
        }
        return result;
    }
}

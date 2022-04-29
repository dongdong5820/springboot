package com.ranlay.core.utils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * 分隔工具
 */
public class SeparatorUtils {
    public static String join(Collection<?> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }
        return Joiner.on(",").join(ids);
    }

    public static List<String> split(String idStr) {
        if(StringUtils.isEmpty(idStr)){
            return null;
        }
        return Splitter.on(",").splitToList(idStr);
    }

    public static List<String> split(String idStr, String separator) {
        if(StringUtils.isEmpty(idStr)){
            return null;
        }
        return Splitter.on(separator).splitToList(idStr);
    }

    public static List<Integer> split2Integer(String idStr) {
        List<String> strings = split(idStr);
        if (CollectionUtils.isEmpty(strings)) {
            return null;
        }
        return Lists.transform(strings, new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                if (null == s || s.equals("")) {
                    return null;
                }
                return Integer.valueOf(s);
            }
        });
    }

    public static List<Long> split2Long(String idStr) {
        List<String> strings = split(idStr);
        if (CollectionUtils.isEmpty(strings)) {
            return null;
        }
        return Lists.transform(strings, new Function<String, Long>() {
            @Override
            public Long apply(String s) {
                if (null == s || s.equals("")) {
                    return null;
                }
                return Long.valueOf(s);
            }
        });
    }
}

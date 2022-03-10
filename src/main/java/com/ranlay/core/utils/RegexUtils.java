package com.ranlay.core.utils;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Ranlay.su
 * @date: 2022-03-08 9:26
 * @description: 正则
 */
public class RegexUtils {
    public static List<String> matchValue(String regex, String content) {
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(content);
        List<String> values = new ArrayList<>();
        while (matcher.find()) {
            String value = matcher.group(1);
            if (!StringUtils.isEmpty(value)) {
                values.add(value);
            }
        }
        return values;
    }
}

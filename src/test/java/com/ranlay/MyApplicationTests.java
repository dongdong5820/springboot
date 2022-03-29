package com.ranlay;

import com.alibaba.fastjson.JSON;
import com.ranlay.core.utils.DigestUtil;
import com.ranlay.core.utils.RandomUtil;
import com.ranlay.core.utils.RegexUtils;
import com.ranlay.core.utils.StringUtil;
import com.ranlay.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Ranlay.su
 * @date: 2021-09-28 10:33
 * @description:
 * @version: 1.0.0
 */
@SpringBootTest
public class MyApplicationTests {
    private Integer duration = 30;

    @Test
    public void testGenerateSign() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("nonce", String.valueOf(RandomUtil.mtRand(1000, 9999)));
        headers.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        headers.put("apiSecret", "ir9Uy5mfoheokXvT");
        System.out.println(headers);
        String sign = DigestUtil.generateSign(headers);
        System.out.println(sign);
    }

    @Test
    public void testStringFunc() {
        String str = "projects/oneplus-community/messages/0:1636028290551095%4ebccc2f4ebccc2f";
        String value = "/";

        String s = StringUtil.lastSubstring(str, value);
        System.out.println(s);

        String format = String.format("%d-%d", 12, "哈哈哈");
        System.out.println(format);
    }

    @Test
    public void testMatchValue() {
        String content = "<div><a data-type=\"@\" data-value=\"159874569\" rel=\"noopener noreferrer\">@春笋测试</a> cvnjjggffhhjjhhgg</div>";
        String REGEX_TAG_AT = "<a data-type=\"@\" data-value=\"(\\d+)\"( rel=\"noopener noreferrer\"){0,1}>";
        List<String> matchValue = RegexUtils.matchValue(REGEX_TAG_AT, content);
        System.out.println(matchValue);
    }

    @Test
    public void testDate() {
        // 毫秒转日期
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy_M");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long time = System.currentTimeMillis();
        System.out.println("当前时间: " + simpleDateFormat2.format(new Date()));
        Calendar calc = Calendar.getInstance();
        calc.add(Calendar.DATE, -duration);
        Date date = calc.getTime();
        time = date.getTime();
        System.out.println(duration + "天前的时间戳(毫秒)：" + time);
        System.out.println(duration + "天前的时间：" + simpleDateFormat2.format(date));

        Long fuid = 1027856867984932868L;
        Long tuid = 1027857012168327168L;
        long max = Math.max(fuid, tuid);
        long min = Math.min(fuid, tuid);
        System.out.println("max: " + max);
        System.out.println("min: " + min);

        String str = "afc47bb699c44409b5ca23144e7256f0";
        System.out.println(Math.abs(str.hashCode()%100));
    }

    @Test
    public void testFastJson() {
        User jack = User.builder()
                .uid(12L)
                .userName("jack")
                .height(175)
                .age(new Date())
                .value("{}")
                .build();
        System.out.println("user: " + JSON.toJSONString(jack));
    }
}

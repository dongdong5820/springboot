package com.ranlay;

import com.ranlay.core.utils.DigestUtil;
import com.ranlay.core.utils.RandomUtil;
import com.ranlay.core.utils.RegexUtils;
import com.ranlay.core.utils.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Ranlay.su
 * @date: 2021-09-28 10:33
 * @description:
 * @version: 1.0.0
 */
@SpringBootTest
public class MyApplicationTests {

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_M");
        Long time = System.currentTimeMillis() + 30*24*3600*1000;
        Date date = new Date(time);
        System.out.println(simpleDateFormat.format(date));

//        String str = "fdadsfjalfdalfd";
//        String str = "c1490648_f4e7_4164_8d04_c4ab38dcc7bd";
        String str = "a2963dbad46a4fc5b015637dcb17e01c";
        System.out.println(Math.abs(str.hashCode()%100));
    }
}

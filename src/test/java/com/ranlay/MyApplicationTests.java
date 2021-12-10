package com.ranlay;

import com.ranlay.core.utils.DigestUtil;
import com.ranlay.core.utils.RandomUtil;
import com.ranlay.core.utils.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
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
}

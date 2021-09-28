package com.ranlay.Utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: Ranlay.su
 * @date: 2021-09-28 10:35
 * @description: 加解密
 * @version: 1.0.0
 */
public class DigestUtil {

    /**
     * md5加密字符串
     * @param raw
     * @return
     */
    public static String encryptMD5(String raw) {
        String hex = "";
        try {
            hex = DigestUtils.md5DigestAsHex(raw.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hex;
    }

    /**
     * 生成api调用的sign
     * @param maps
     * @return
     */
    public static String generateSign(Map<String, String> maps) {
        List<String> rawList = new LinkedList<>();
        for (Map.Entry<String, String> entry :maps.entrySet()) {
            rawList.add(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
        String apiSign = ListUtil.implode(rawList, '&');
        return DigestUtil.encryptMD5(apiSign);
    }
}

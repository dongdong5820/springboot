package com.ranlay;

import com.alibaba.fastjson.JSON;
import com.ranlay.core.model.req.msg.MsgSystemNoticeReqDTO;
import com.ranlay.core.utils.DigestUtil;
import com.ranlay.core.utils.RandomUtil;
import com.ranlay.core.utils.RegexUtils;
import com.ranlay.core.utils.StringUtil;
import com.ranlay.pojo.User;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
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
        String REGEX_THREAD = ".*/thread\\?id=(\\d+)";
        String jumpUrl = "https://communitytest.wanyol.com/thread?id=860172952307499011";
        matchValue = RegexUtils.matchValue(REGEX_THREAD, jumpUrl);
        System.out.println(matchValue);
    }

    @Test
    public void testMsgDateMigrate() {
        List<MsgSystemNoticeReqDTO> lists = getMsgSystemNoticeLists();
        System.out.println(JSON.toJSONString(lists));
        lists.forEach(dto -> {
            formatMsgSystemNotice(dto);
        });
        System.out.println(JSON.toJSONString(lists));
    }

    /**
     * 获取消息列表
     * @return
     */
    private List<MsgSystemNoticeReqDTO> getMsgSystemNoticeLists() {
        List<MsgSystemNoticeReqDTO> data = new ArrayList<>();
        // 帖子审核不通过
        data.add(MsgSystemNoticeReqDTO.builder()
                .type(1)
                .jumpUrl("https://communitytest.wanyol.com/thread?id=860172952307499011")
                .build());

        // 用户资料审核不通过
        data.add(MsgSystemNoticeReqDTO.builder()
                .type(1)
                .jumpUrl("https://communitytest.wanyol.com/user-main/2086814084/index")
                .build());

        // 关注
        data.add(MsgSystemNoticeReqDTO.builder()
                .type(2)
                .jumpUrl("https://communitytest.wanyol.com/user-main/2086653950/index")
                .build());

        return data;
    }

    /**
     * 解析消息
     * @param dto
     */
    private void formatMsgSystemNotice(MsgSystemNoticeReqDTO dto) {
        Integer type = dto.getType();
        String jumpUrl = dto.getJumpUrl();
        String REGEX_USER = ".*/user-main/(\\d+)/.*";
        List<String> matchValue = RegexUtils.matchValue(REGEX_USER, jumpUrl);
        if (!matchValue.isEmpty()) {
            dto.setType(type == 2 ? 10 : 4);
            dto.setJumpUrl(this.getJumpUrl(dto.getJumpUrl(), "/user-main"));
            dto.setContentId(Long.parseLong(matchValue.get(0)));
            return;
        }
        String REGEX_THREAD = ".*/thread\\?id=(\\d+)";
        matchValue = RegexUtils.matchValue(REGEX_THREAD, jumpUrl);
        if (!matchValue.isEmpty()) {
            dto.setJumpUrl(this.getJumpUrl(dto.getJumpUrl(), "/thread?id="));
            dto.setContentId(Long.parseLong(matchValue.get(0)));
            return;
        }
    }

    /**
     * 获取url
     * @param jumpUrl
     * @param search
     * @return
     */
    private String getJumpUrl(String jumpUrl, String search) {
        int i = jumpUrl.indexOf(search);
        return jumpUrl.substring(i);
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

    @Test
    public void testJsoup() {
        String content = "<div><a data-type=\"@\" data-value=\"1030040673097613319\" rel=\"noopener noreferrer\">@E16372070.asd</a>   come on!</div>";
        String clean = Jsoup.clean(content, Safelist.simpleText());
        System.out.println(content);
        System.out.println(clean);
    }
}

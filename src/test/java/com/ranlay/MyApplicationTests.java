package com.ranlay;

import com.alibaba.fastjson.JSON;
import com.ranlay.core.model.req.msg.MsgSystemNoticeReqDTO;
import com.ranlay.core.utils.*;
import com.ranlay.pojo.AddFeedbackRespDTO;
import com.ranlay.pojo.FileUrlDTO;
import com.ranlay.pojo.Person;
import com.ranlay.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    public void testTypeConversion() {
        // 基本数据类型转换
//        long a = 10; int b = (int) a;
//        System.out.println(b);
        int a = 10; long b = (long) a;
        System.out.println(b);
        // 包装类型转换
        Long m = 10L; Integer n = Integer.valueOf(m.intValue());
        System.out.println(n);
        Integer i = 10; Long j = Long.valueOf(i.longValue());
        System.out.println(j);
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Jack");
    }

    @Test
    public void testStringFunc() {
        String str = "projects/oneplus-community/messages/0:1636028290551095%4ebccc2f4ebccc2f";
        String value = "/";

        // null不是String的实例, 返回false
        System.out.println(null instanceof String);

        String s = StringUtil.lastSubstring(str, value);
        System.out.println(s);

//        s = "[OnePlus Community]Task reward";
        s = "[OnePlus Community]Task reward\"First like on a thread\"";
//        s = "[OnePlus Community]Task reward\"First \"like\" on a thread\"";
        String pattern = "\\[OnePlus Community\\](Task reward|Point reward|Point deduction|Raffle reward|Raffle deduction)(\\\")?";
        String d = s.replaceAll(pattern, "");
        System.out.println(d);
        if (!StringUtil.isEmpty(d)) {
            d = d.substring(0, d.length() -1);
        }
        System.out.println(d);

//        String format = String.format("%d-%d", 12, "哈哈哈");
//        System.out.println(format);
    }

    @Test
    public void testMatchValue() {
        String REGEX_TAG_AT;
        List<String> matchValue;
        String content = "<div><a data-type=\"@\" data-value=\"159874569\" rel=\"noopener noreferrer\">@春笋测试</a> cvnjjggffhhjjhhgg</div>";
        REGEX_TAG_AT = "<a data-type=\"@\" data-value=\"(\\d+)\"( style=\"display:inline-block;\"){0,1}( rel=\"noopener noreferrer\"){0,1}>";
        matchValue = RegexUtils.matchValue(REGEX_TAG_AT, content);
        System.out.println(matchValue);
        content = "<div><p>121212</p><p> <a data-type=\"@\" data-value=\"1048723605303590917\" style=\"display:inline-block;\" rel=\"noopener noreferrer\">@J1650437429415</a>  come on</p><p> <a data-type=\"@\" data-value=\"2086780564\" style=\"display:inline-block;\" rel=\"noopener noreferrer\">@Hurricane_T</a>  come too.</p></div>";
        matchValue = RegexUtils.matchValue(REGEX_TAG_AT, content);
        System.out.println(matchValue);
        content = "<div><p><a data-type=\"@\" data-value=\"1034172527573991430\" style=\"display:inline-block;\" rel=\"noopener noreferrer\">@Zach X.</a> , <a data-type=\"@\" data-value=\"1034172533739880452\" style=\"display:inline-block;\" rel=\"noopener noreferrer\">@dsmonteiro</a> , <a data-type=\"@\" data-value=\"1034172554585309184\" style=\"display:inline-block;\" rel=\"noopener noreferrer\">@YRJ</a> , <a data-type=\"@\" data-value=\"1034172540895100931\" style=\"display:inline-block;\" rel=\"noopener noreferrer\">@Cheetosdust</a> </p><p> </p><p>More and more people ask what to do with the issues they have and how to report them. Since the new forums, there is no feedback section yet, we only can assume bug hunters read along and jump in where necessary. But assumptions didn’t build the Eifel tower.</p><p> </p><p>I know that a feedback section is planned for August and I do hope it will be ready by then but what about people wanting to report the bugs and issues they are facing now? Post them somewhere or create a thread and hope it gets picked up? I don’t think this is the way to go.</p><p> </p><p>With all the new updates and the issues, people are facing for whatever reason, I think there should be a temporary procedure that works for all regions. We can’t wait for a month to give feedback or send logs, the present procedures are already vague as it is. </p><p> </p><p>Could someone please look into this and let us know?</p></div>";
        matchValue = RegexUtils.matchValue(REGEX_TAG_AT, content);
        System.out.println(matchValue);

        String REGEX_THREAD = ".*/thread\\?id=(\\d+)";
        String jumpUrl = "https://communitytest.wanyol.com/thread?id=860172952307499011";
        matchValue = RegexUtils.matchValue(REGEX_THREAD, jumpUrl);
        System.out.println(matchValue);
        String url = "/comment?tid=1030045736880832512&cid=1030047643259109379&rid=0";
        REGEX_TAG_AT = "tid=(\\d+)&cid=(\\d+)&rid=(\\d+)";
        matchValue = RegexUtils.matchGroupValue(REGEX_TAG_AT, url);
        if (matchValue.size() == 3) {
            String tid = matchValue.get(0);
            String cid = matchValue.get(1);
            Long rid = Long.valueOf(matchValue.get(2));
            String deepLink = rid.equals(0L) ?
                    String.format("/app/comment?threadId=%s&commentId=%s", tid, cid) :
                    String.format("/app/comment?replyId=%s&commentId=%s", rid, cid);
            System.out.println(deepLink);
        }
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

        List<Person> personList = this.getPersonList();
        String personStr = JSON.toJSONString(personList);
        System.out.println("personList: " + personStr);

        List<Person> people = JSON.parseArray(personStr, Person.class);
        System.out.println(people);

        List<Long> oldUidList = new ArrayList<>();
        oldUidList.add(123456L);
//        String uids = "[12569874,569874563]";
        String uids = "[]";
        List<Long> uidList = JSON.parseArray(uids, Long.class);
        System.out.println(uidList);
        System.out.println(CollectionUtils.isEmpty(uidList));
        oldUidList.addAll(uidList);
        System.out.println(oldUidList);

        // json字符串比实体字段多
        String json = "{\"feedbackId\":1085786080406405123,\"feedbackType\":1,\"feedbackContent\":\"fasdfaf\",\"userId\":\"2086633222\",\"processStatus\":1,\"createTime\":1652066516}";
        AddFeedbackRespDTO addFeedbackRespDTO = JSON.parseObject(json, AddFeedbackRespDTO.class);
        System.out.println(addFeedbackRespDTO);
        // json字符串比实体字段少
        json = "{\"ocsKey\":\"ece7be6f-0fe1-4ee7-bc73-269de4ce8e2b\",\"url\":\"http://s3.ap-southeast-1.amazonaws.com/fms-manage-files/ece7be6f-0fe1-4ee7-bc73-269de4ce8e2b?AWSAccessKeyId=AKIAUYUFOQPYAF2BXTMF&Expires=1657787027&Signature=vc3h6SFFIZCYtemvdR7MumkDWLU%3D\"}";
        FileUrlDTO fileUrlDTO = JSON.parseObject(json, FileUrlDTO.class);
        System.out.println(fileUrlDTO);
    }

    private List<Person> getPersonList() {
//        return Collections.emptyList();
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person(1L, "Jack");
        personList.add(p1);

        Person p2 = new Person(2L, "Lucy");
        personList.add(p2);

        Person p3 = new Person(1L, "Jack");
        personList.add(p3);

        return personList;
    }

    @Test
    public void testJsoup() {
        String content = "<div><a data-type=\"@\" data-value=\"1030040673097613319\" rel=\"noopener noreferrer\">@E16372070.asd</a>   come on!</div>";
        String clean = Jsoup.clean(content, Safelist.simpleText());
//        System.out.println(content);
//        System.out.println(clean);

        content = "<div><p> <a data-type=\"@\" data-value=\"2086855474\" style=\"display:inline-block;\" rel=\"noopener noreferrer\">@lijule-14446317317</a>  这是一篇短文本 帖子</p></div>hello & world !!! < xxx >";
//        content = "<div>hahahha</div>, hello & world !!! < xxx >";
//        clean = Jsoup.clean(Jsoup.parse(content).text(), Safelist.simpleText());
        clean = Jsoup.parse(Jsoup.clean(content, Safelist.simpleText())).text();
        String substring = clean.substring(0, 21);
        System.out.println(content);
        System.out.println(clean);
        System.out.println(clean.length());
        System.out.println(substring);
    }

    @Test
    public void testOptional() {
        Long id = 2L;
        Optional<Person> first = this.getPersonList().stream().filter(item -> item.getId().equals(id)).findFirst();
        String name = first.isPresent() ? first.get().getName() : null;
        System.out.println(name);
        System.out.println(StringUtil.isEmpty(name));
    }

    @Test
    public void testBitOperation() {
        BitOperationUtil.run();
    }

    @Test
    public void testToMap() {
        // Integer 越界处理
        String str = "[69696969699696,5689]";
        List<String> list = JSON.parseArray(str, String.class);
        List<Integer> uidList = new ArrayList<>();
        for (String s : list) {
            try {
                Integer integer = Integer.valueOf(s);
                uidList.add(integer);
            } catch (Exception e) {
                System.out.println(s);
            }
        }
        System.out.println(list);
        System.out.println(uidList);

        List<Person> personList = this.getPersonList();
        System.out.println(personList);
        List<Long> userIds = personList.stream().map(Person::getId).collect(Collectors.toList());
        System.out.println(userIds);
        userIds = userIds.stream().distinct().collect(Collectors.toList());
        System.out.println(userIds);
        // 列表中有重复对象，转map时会报 Duplicate key xxx
//        Map<Long, String> map = personList.stream().collect(Collectors.toMap(Person::getId, a -> a.getName()));
//        System.out.println(map);
    }

    /**
     * 测试集合
     */
    @Test
    public void testCollection() {
        // set集合
        Set<Long> uidSet = new HashSet<>();
        System.out.println(uidSet.add(1083617868524814353L));
        System.out.println(uidSet.add(1083617862602457098L));
        System.out.println(uidSet.add(1083617681005871109L));
        System.out.println(uidSet.add(1083617868524814353L));
        System.out.println(uidSet);
    }

    /**
     * 数据库分表键
     */
    @Test
    public void testTableSharding() {
        // 评论主键缓存key
        //        String str = "1074912972346753027_1084102950805045250";
//        String str = "1074912972346753027_1084103038692491266";
        String str = "865_30286";
        System.out.println(DigestUtils.md5Hex(str));

        // 私信分组key
        Long fuid = 1067640179318063107L;
        Long tuid = 1345690597L;
        Long uidBig = Math.max(fuid, tuid);
        Long uidLittle = Math.min(fuid, tuid);
        int i = ((uidLittle.hashCode() + uidBig.hashCode()) >>> 1) % 100;
        System.out.println(i);

        // push_mac_token 分组key
        String mac = "45381996e93d42bbbd53667f85ead46b";
        int a = mac.hashCode() % 100;
        System.out.println(a);
    }

    /**
     * feedback系统的sign
     */
    @Test
    public void testFeedback() {
        String appId = "oneplus-community";
        String appSecret = "f38fsj845kgfsu9i";
        String lang = "en";
        long ts = System.currentTimeMillis() / 1000;
        String url = "/api/feedback/getUploadSign";
        String plainText = String.format("%s%s%s", url, ts, appSecret);
        System.out.println(plainText);
        String sign = DigestUtils.sha256Hex(plainText);
        String format = String.format("appId:%s\nlang:%s\nts:%s\nsign:%s", appId, lang, ts, sign);
        System.out.println(format);
    }
}
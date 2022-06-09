package com.ranlay;

import com.ranlay.core.utils.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: Ranlay.su
 * @date: 2022-05-31 11:45
 * @description: java 解析 html
 * @version: 1.0.0
 */
@SpringBootTest
public class JsoupTests {
    private static final String LINK_MEMBERS = "members";
    private static final String LINK_FORUMS = "forums";
    /**
     * 不存在的ID
     */
    private static final List<Long> NOT_EXIST_ID = Arrays.asList(154L, 641296L);

    private String memberUrl = "https://community.oneplus.com/user-main/%s/index";
    private String circleUrl = "https://community.oneplus.com/circleDetail?id=%s";
    private String html = "<div><strong>Rules:</strong><br>\n" +
            "<em>Hey hey</em><br>\n" +
            "<span style=\"text-decoration: underline\">happening</span><br>\n" +
            "<span style=\"text-decoration: line-through\">Shenzhen</span><br>\n" +
            "<span style=\"color: #ff0000\">Weekly</span><br>\n" +
            "<span style=\"font-family: 'Georgia'\">stylish</span><br>\n" +
            "Umbrellas<br>\n" +
            "<ul>\n" +
            "<li>After the rollback is complete, click Restart.</li>\n" +
            "<li>Rollback successful.</li>\n" +
            "</ul><img data-type=\"file\" data-value=\"320644\"><br>\n" +
            "ooooo<br>\n" +
            "<img data-type=\"file\" data-value=\"5698745\"><br>\n" +
            "hahaha<br>\n" +
            "<video data-type=\"youtube\" data-value=\"https://www.youtube.com/watch?v=h_D3VFfhvs4\"></video><br>\n" +
            "hohoh<br>\n" +
            "<a data-type=\"link\" data-value=\"https://open.spotify.com/embed?uri=spotify:track:15OVL6MDpdHHIVUcd9mfHM\">https://open.spotify.com/embed?uri=spotify:track:15OVL6MDpdHHIVUcd9mfHM</a><br>\n" +
            "<a class=\"atUser\" data-type=\"@\" data-value=\"641296\">@llano777</a><br>\n" +
            "xxxx<br>\n" +
            "        <img data-type=\"file\" data-value=\"15869\"><br>\n" +
            "Hey friends,<br>\n" +
            "<br>\n" +
            "*Review Update* October 21<br>\n" +
            "Check out the following reviews by our Reviewer Squad.<br>\n" +
            "        <a data-type=\"link\" data-value=\"https://community.oneplus.com/terms-and-conditions\">https://community.oneplus.com/terms-and-conditions</a><br>\n" +
            "<a data-type=\"link\" data-value=\"https://community.oneplus.com/thread?id=1510337\">https://forums.oneplus.com/threads/...s-the-tiny-mighty-action-cam-a-go-go.1510337/</a><br>\n" +
            "<a data-type=\"link\" data-value=\"https://community.oneplus.com/thread?id=1510337\">[The Lab²] Insta360 GO 2 - Is The Tiny Mighty Action Cam a Go Go?</a> by <a class=\"internalLink\" type=\"members\" data-type=\"link\" data-value=\"357359\">obakesan</a><br>\n" +
            "<a data-type=\"link\" data-value=\"https://forms.office.com/Pages/ResponsePage.aspx?id=nZAjBGwpPkarXOWFOlGN-DosR4EFi49MgyXzfJo-FmlUMVA5MzY3TzFWWDVDNEhSVVVGSzBYVjJKTC4u\">here</a><br>\n" +
            " <a data-type=\"link\" data-value=\"mailto:ranlay.su@oneplus.com\">发送邮件ranlay.su</a><br>\n" +
            "<a class=\"internalLink\" type=\"forums\" data-type=\"link\" data-value=\"154\">oneplus-8t</a><br> \n" +
            "Don't hesitate, come and join us!<br>\n" +
            "so keep an eye for that! ????<br>\n" +
            "<a class=\"atUser\" data-type=\"@\" data-value=\"2225727\">@weare</a><br>\n" +
            "<a class=\"internalLink\" type=\"forums\" data-type=\"link\" data-value=\"168\">OnePlus 10 Series</a><br> \n" +
            "<em><strong>@Shubham_Ag. said:</strong><br>The OnePlus Buds Pro has been a perfect match for me this year. I was longing for a pair of TWS which gives one of the best sound signatures in the market without burning a hole in the pocket and Oh boy, what a beast they are!\n" +
            " \n" +
            "The sound output is amazing, and the thing that surprised me the most w...</em></div>";

    @Test
    public void testParseHtml() {
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();

        Elements atElements = body.select("a.atUser");
        List<Long> uidList = atElements.stream().map(e -> {
            return Long.valueOf(e.attr("data-value"));
        }).collect(Collectors.toList());
        List<Long> nodeIdList = new ArrayList<>();
        Elements linkElements = body.select("a.internalLink");
        for (Element element : linkElements) {
            String type = element.attr("type");
            Long dataId = Long.valueOf(element.attr("data-value"));
            if (Objects.isNull(type)) {
                continue;
            }
            switch (type) {
                case LINK_MEMBERS:
                    uidList.add(dataId);
                    break;
                case LINK_FORUMS:
                    nodeIdList.add(dataId);
                    break;
            }
        }
        Map<Long, Long> userIdMap = this.queryDataIdMap(uidList);
        Map<Long, Long> nodeIdMap = this.queryDataIdMap(nodeIdList);
        System.out.println("userIdMap: " + userIdMap);
        System.out.println("nodeIdMap: " + nodeIdMap);

        // @ 人
        for (Element element : atElements) {
            Long uid = Long.valueOf(element.attr("data-value"));
            Long newUid = userIdMap.get(uid);
            // 删除元素
            if (Objects.isNull(newUid)) {
                System.out.println(String.format("@人 uid: %s not found, delete element ------", uid));
                element.remove();
                continue;
            }
            // 替换uid
            element.removeClass("atUser").attr("data-value", newUid.toString());
        }
        // 内链
        for (Element element : linkElements) {
            String type = element.attr("type");
            Long dataId = Long.valueOf(element.attr("data-value"));
            System.out.println(String.format("type: %s, dataId: %s ------", type, dataId));
            if (Objects.isNull(type)) {
                element.remove();
                continue;
            }
            String dataValue=null;
            switch (type) {
                case LINK_MEMBERS:
                    Long newUid = userIdMap.get(dataId);
                    if (Objects.nonNull(newUid)) {
                        dataValue = String.format(memberUrl, newUid);
                    }
                    break;
                case LINK_FORUMS:
                    Long circleId = nodeIdMap.get(dataId);
                    if (Objects.nonNull(circleId)) {
                        dataValue = String.format(circleUrl, circleId);
                    }
                    break;
                default:
            }
            if (Objects.isNull(dataValue)) {
                System.out.println(String.format("type: %s, dataId: %s not found, delete element ------", type, dataId));
                element.remove();
                continue;
            }
            element.removeClass("internalLink").removeAttr("type").attr("data-value", dataValue);
        }
        System.out.println("================================");
        String div = body.html();
        System.out.println(div);
    }

    private Map<Long, Long> queryDataIdMap(List<Long> uidList) {
        Map<Long, Long> dataIdMap = new HashMap<>(uidList.size());
        if (CollectionUtils.isEmpty(uidList)) {
            return dataIdMap;
        }
        for (Long uid : uidList) {
            if (NOT_EXIST_ID.contains(uid)) {
                continue;
            }
            dataIdMap.put(uid, (new Random()).nextLong());
        }
        return dataIdMap;
    }
}
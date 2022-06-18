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
//    private String html = "<div><strong>Rules:</strong><br>" +
//            "<em>Hey hey</em><br>" +
//            "<span style=\"text-decoration: underline\">happening</span><br>" +
//            "<span style=\"text-decoration: line-through\">Shenzhen</span><br>" +
//            "<span style=\"color: #ff0000\">Weekly</span><br>" +
//            "<span style=\"font-family: 'Georgia'\">stylish</span><br>" +
//            "Umbrellas<br>" +
//            "<ul>" +
//            "<li>After the rollback is complete, click Restart.</li>" +
//            "<li>Rollback successful.</li>" +
//            "</ul><img data-type=\"file\" data-value=\"320644\"><br>" +
//            "ooooo<br>" +
//            "<img data-type=\"file\" data-value=\"5698745\"><br>" +
//            "hahaha<br>" +
//            "<video data-type=\"youtube\" data-value=\"https://www.youtube.com/watch?v=h_D3VFfhvs4\"></video><br>" +
//            "hohoh<br>" +
//            "<a data-type=\"link\" data-value=\"https://open.spotify.com/embed?uri=spotify:track:15OVL6MDpdHHIVUcd9mfHM\">https://open.spotify.com/embed?uri=spotify:track:15OVL6MDpdHHIVUcd9mfHM</a><br>" +
//            "<a class=\"atUser\" data-type=\"@\" data-value=\"641296\">@llano777</a><br>" +
//            "xxxx<br>" +
//            "        <img data-type=\"file\" data-value=\"15869\"><br>" +
//            "Hey friends,<br>" +
//            "<br>" +
//            "*Review Update* October 21<br>" +
//            "Check out the following reviews by our Reviewer Squad.<br>" +
//            "        <a data-type=\"link\" data-value=\"https://community.oneplus.com/terms-and-conditions\">https://community.oneplus.com/terms-and-conditions</a><br>" +
//            "<a data-type=\"link\" data-value=\"https://community.oneplus.com/thread?id=1510337\">https://forums.oneplus.com/threads/...s-the-tiny-mighty-action-cam-a-go-go.1510337/</a><br>" +
//            "<a data-type=\"link\" data-value=\"https://community.oneplus.com/thread?id=1510337\">[The Lab²] Insta360 GO 2 - Is The Tiny Mighty Action Cam a Go Go?</a> by <a class=\"internalLink\" type=\"members\" data-type=\"link\" data-value=\"357359\">obakesan</a><br>" +
//            "<a data-type=\"link\" data-value=\"https://forms.office.com/Pages/ResponsePage.aspx?id=nZAjBGwpPkarXOWFOlGN-DosR4EFi49MgyXzfJo-FmlUMVA5MzY3TzFWWDVDNEhSVVVGSzBYVjJKTC4u\">here</a><br>" +
//            " <a data-type=\"link\" data-value=\"mailto:ranlay.su@oneplus.com\">发送邮件ranlay.su</a><br>" +
//            "<a class=\"internalLink\" type=\"forums\" data-type=\"link\" data-value=\"154\">oneplus-8t</a><br> " +
//            "Don't hesitate, come and join us!<br>" +
//            "so keep an eye for that! ????<br>" +
//            "<a class=\"atUser\" data-type=\"@\" data-value=\"2225727\">@weare</a><br>" +
//            "<a class=\"internalLink\" type=\"forums\" data-type=\"link\" data-value=\"168\">OnePlus 10 Series</a><br> " +
//            "<em><strong>@Shubham_Ag. said:</strong><br>The OnePlus Buds Pro has been a perfect match for me this year. I was longing for a pair of TWS which gives one of the best sound signatures in the market without burning a hole in the pocket and Oh boy, what a beast they are!" +
//            " " +
//            "The sound output is amazing, and the thing that surprised me the most w...</em></div>";
    private String html = "<ul>\n" +
        "<li>4 winners per month will receive the <strong>Buds Z2</strong>.\n" +
        "</li>\n" +
        "<li><span style=\\\"color: rgb(255, 0, 0)\\\">New</span>: The winners will all be announced at the beginning of the following month.\n" +
        "<ol>\n" +
        "<li>Those who won 3 to 5 times in one year will be rewarded with a lens for a phone (within USD500).</li>\n" +
        "</ol>\n" +
        "</li>\n" +
        "<li><span style=\\\"color: rgb(255, 0, 0)\\\">Extra special</span> note from <span>@Crystal Z.</span> and the OnePlus photography team:\n" +
        "<ul>\n" +
        "<li>Those who won 3 to 5 times in one year will be rewarded with a lens for a phone (within USD500).</li>\n" +
        "<li>Those who won 6 or more times will be rewarded with a OnePlus phone</li>\n" +
        "</ul>\n" +
        "</li>\n" +
        "</ul>";

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
        this.parseAtData(atElements, userIdMap);
        // 内链
        this.parseInternalLinkData(linkElements, userIdMap, nodeIdMap);
        // 嵌套 ul|ol li
        Elements ulElements = body.select("li ul");
        this.parseTagList(ulElements);
        Elements olElements = body.select("li ol");
        this.parseTagList(olElements);

        System.out.println("================================");
        doc.outputSettings().prettyPrint(false);
        String div = body.html();
        System.out.println(div.length());
        System.out.println(div);
        System.out.println("================================");
        String s = div.replaceAll("", "");
        System.out.println(s.length());
        System.out.println(s);
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

    /**
     * 处理 @人
     * @param atElements
     * @param userIdMap
     */
    private void parseAtData(Elements atElements, Map<Long, Long> userIdMap) {
        for (Element element : atElements) {
            Long uid = Long.valueOf(element.attr("data-value"));
            Long newUid = userIdMap.get(uid);
            String userName = element.text();
            // 删除元素
            if (Objects.isNull(newUid)) {
                System.out.println(String.format("@人 uid: %s username: %s not found, delete element ------", uid, userName));
                element.tagName("span").removeClass("atUser").removeAttr("data-type").removeAttr("data-value");
//                element.remove();
                continue;
            }
            // 替换uid
            element.removeClass("atUser").attr("data-value", newUid.toString());
        }
    }

    /**
     * 处理 内链
     * @param linkElements
     * @param userIdMap
     * @param nodeIdMap
     */
    private void parseInternalLinkData(Elements linkElements
        , Map<Long, Long> userIdMap
        , Map<Long, Long> nodeIdMap) {
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
                element.tagName("span").removeClass("internalLink").removeAttr("type").removeAttr("data-type").removeAttr("data-value");
//                element.remove();
                continue;
            }
            element.removeClass("internalLink").removeAttr("type").attr("data-value", dataValue);
        }
    }

    /**
     * 嵌套 ul ol li标签
     * @param listElements
     */
    private void parseTagList(Elements listElements) {
        for (Element element : listElements) {
            String html = element.html();
            html = html.replaceAll("<li>", "<p>")
                    .replaceAll("</li>", "</p>")
                    .replaceAll("<ul>", "<span>")
                    .replaceAll("</ul>", "</span>")
                    .replaceAll("<ol>", "<span>")
                    .replaceAll("</ol>", "</span>");
            element.tagName("span").html(html);
        }
    }
}
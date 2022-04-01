package com.ranlay.Controller.antMatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Ranlay.su
 * @date: 2022-04-01 14:30
 * @description: spring mvc ant matcher规则
 * @version: 1.0.0
 */
@Controller
@ResponseBody
@RequestMapping("/ant-matcher")
@Slf4j
public class AntController {
    /**
     * ant matcher规则
     * ? 匹配1个字符
     * * 匹配0个或多个字符
     * ** 匹配路径中的0个或多个目录
     * {spring:[a-z]+} 将正则表达式[a-z]+匹配到的值，赋值给名为spring的路径变量
     * PS: 必须是完全匹配，在springMVC中只有完全匹配才会进入controller层的方法
     */

    /**
     * index    404错误(必须要有一个字符)
     * index/   404错误(不能为"/")
     * indexab  404错误(不能是多个字符)
     * indexa   true 输出11
     * @return
     */
    @RequestMapping("/index?")
    public String index() {
        String a = "11";
        System.out.println(a);
        return a;
    }

    /**
     * index    true(输出22)
     * index/   true(可以为"/")
     * indexa   true
     * indexabc true(可以为多个字符)
     * index/a  404错误("/a"是一个路径)
     * @return
     */
    @RequestMapping("/index*")
    public String index1() {
        String a = "22";
        System.out.println(a);
        return a;
    }

    /**
     * index/a          true(可以为0个目录)
     * index/x/a        true(可以为1个目录)
     * index/x/z/x/a    true(可以为多个目录)
     * @return
     */
    @RequestMapping("index/**/a")
    public String index2() {
        String a = "33";
        System.out.println(a);
        return a;
    }

    /**
     * index/ab     true 输出ab
     * index/abbaaa true 输出abbaaa
     * index/a      true 输出a
     * index/ac     404错误(不能有c)
     * @param uname
     * @return
     */
    @RequestMapping("/index/{username:[a-b]+}")
    public String index3(@PathVariable("username") String uname) {
        System.out.println(uname);
        return uname;
    }

    @RequestMapping("/test/*")
    public String test(HttpServletRequest request) {
        // /ant-matcher/test/a?uid=1212&page=1&size=10
        String remoteHost = request.getRemoteHost();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        // 路径匹配
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String pattern = "*/test/*";
        boolean match = pathMatcher.match(pattern, requestURI);
        log.info("match:{}, remoteHost:{}, requestURI:{}, request:{}", match, remoteHost, requestURI, request);
        String res = String.format("requestURL: %s</br>" +
                "remoteHost: %s</br>" +
                "requestURI: %s</br>" +
                "queryString: %s</br>",
                requestURL, remoteHost, requestURI, queryString);
        return res;
    }
}

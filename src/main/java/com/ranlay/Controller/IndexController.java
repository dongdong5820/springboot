package com.ranlay.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ranlay.su
 * @date 2021-04-15 10:49
 * @desc 首页类
 */
@Controller
public class IndexController {
    /**
     * 定义请求路径为： localhost:8080/hello, 下面是对应这个路径的处理方法
     * RequestParam 用于接收页面传递过来的参数，例如 localhost:8080/hello?name=小明
     * model 是spring内置的对象，用来处理视图
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name") String name, Model model) {
        // 将浏览器的请求参数中的name加入到model中，这样就能在模板的html中获取到
        model.addAttribute("name", name);
        // 返回的index是html模板名称
        return "index";
    }
}

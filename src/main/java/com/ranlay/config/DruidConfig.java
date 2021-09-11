package com.ranlay.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Ranlay.su
 * @date: 2021-07-06 14:41
 * @description: Druid配置
 * @version: 1.0
 */
@Configuration
public class DruidConfig {
    /**
     * 将自定义的 Druid数据源添加到容器中，不再让 springboot自动创建
     * 绑定全局配置文件中的 druid 数据源属性到 com.alibaba.druid.pool.DruidDataSource从而让他们生效
     * @ConfigurationProperties(prefix = "spring.datasource"): 作用就是将 全局配置中前缀为
     * spring.datasource的属性值注入到 com.alibaba.druid.pool.DruidDataSource的同名参数中
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置 Druid 监控管理后台的servlet
     * 内置servlet 容器时没有web.xml文件，所以用 springboot 注册 servlet 方式
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        /**
         * 这些参数可以在 com.alibaba.druid.support.http.StatViewServlet 的父类
         * com.alibaba.druid.support.http.ResourceServlet 找到
         */
        Map<String, String> initParams = new HashMap<>();
        // 后台登录账号  http://localhost:8080/druid/login.html
        initParams.put("loginUsername", "admin");
        // 后台登录密码
        initParams.put("loginPassword", "123456");

        // 为空或者null时，表示允许所有访问
        initParams.put("allow","");

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置 Druid 监控之 web 监控的 filter
     * WebStatFilter : 用于配置web和Druid数据源之间的管理关联监控统计
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        // exclusions: 设置哪些请求过滤排除，不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*,/jdbc/*");
        bean.setInitParameters(initParams);

        // "/*" 表示过滤所有请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}

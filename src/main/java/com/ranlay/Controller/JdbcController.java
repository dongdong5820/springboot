package com.ranlay.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: Ranlay.su
 * @date: 2021-07-05 17:47
 * @description: springboot整合jdbc
 * @version: 1.0
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    /**
     * Spring Boot 默认提供了数据源，默认是 org.springframework.jdbc.core.JdbcTemplate
     * JdbcTemplate 中会自己注入数据源，用于简化 JDBC 操作
     * 还能避免一些常见的错误，使用起来也不用自己关闭数据库连接
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public String test() {
        return "test ok";
    }

    @GetMapping("/userList")
    public List<Map<String, Object>> userList() {
        String sql = "select * from employee";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return maps;
    }

    @GetMapping("/addUser")
    public String addUser() {
        long time = System.currentTimeMillis() / 1000;
        String sql = "INSERT INTO `employee` (`username`,`password`,`sex`,`birthday`)" +
                " VALUES ('黎明','654987','1'," + time + ")";
        jdbcTemplate.update(sql);
        return "addOk";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        // 修改语句
        String sql = "update employee set username=?,password=? where id=" + id;
        // 绑定的数据
        Object[] objects = new Object[2];
        objects[0] = "彭于晏";
        objects[1] = "234567";
        jdbcTemplate.update(sql, objects);

        return "updateOk";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from employee where id=?";
        jdbcTemplate.update(sql, id);

        return "deleteOk";
    }
}

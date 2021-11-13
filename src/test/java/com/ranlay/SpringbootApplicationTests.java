package com.ranlay;

import com.alibaba.druid.pool.DruidDataSource;
import com.ranlay.core.utils.DateUtil;
import com.ranlay.core.service.ExecutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.unit.DataUnit;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    DataSource dataSource;
    @Resource
    ExecutionService executionService;

    @Test
    public void contextLoads() throws SQLException {
        // com.zaxxer.hikari.HikariDataSource
        System.out.println("数据源类: " + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        // HikariProxyConnection@170050776 wrapping com.mysql.cj.jdbc.ConnectionImpl@45cd7bc5
        System.out.println("数据库连接: " + connection);

        DruidDataSource druidDataSource = (DruidDataSource) this.dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

        connection.close();
    }

    /**
     * 测试切面 Aspect
     */
    @Test
    public void testExecution() {
        executionService.add("1234");
        System.out.println("======================");

        executionService.test();
        System.out.println("======================");

        executionService.hello("玉麒麟卢俊义");
    }

    /**
     * 测试日期类
     */
    @Test
    public void testDateUtils() {
        long millis = System.currentTimeMillis();
//        String formatStr = "yyyy-MM-dd HH:mm:ss";
        String formatStr = "H";
        List<String> timezones = Arrays.asList(
                "Asia/Hong_Kong",
                "America/Chicago",
                "Africa/Casablanca",
                "Asia/Seoul",
                "Europe/London"
        );
        timezones.forEach(tz -> {
            System.out.println(tz + "\t" + DateUtil.convertTimeToString(millis, formatStr, tz));
        });
    }
}

package com.ranlay.mysql.gensql;

/**
 * @author: Ranlay.su
 * @date: 2022-02-17 14:52
 * @description: 生成分表结构的SQL
 */
public class GenerateSql {
    public static void main(String[] args) {
        int num = 100;
        String sql = "";
        String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] years = new String[]{"2022"};

        System.out.println("USE `oppo_sns_overseas_msg`;");
        for (String month : months) {
            sql = String.format("DROP TABLE IF EXISTS `push_history_2022%s`;", month);
//            System.out.println(sql);
        }

        // push_history 表
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = String.format("%s_%s", year, i);
                sql = String.format("DROP TABLE IF EXISTS `push_history_%s`;\n" +
                        "CREATE TABLE `push_history_%s` (\n" +
                        "  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',\n" +
                        "  `uid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '用户ID',\n" +
                        "  `reg_id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '推送token',\n" +
                        "  `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '消息标题',\n" +
                        "  `content` TEXT COMMENT '消息内容',\n" +
                        "  `tracker_id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '埋点标识',\n" +
                        "  `status` TINYINT(2) NOT NULL DEFAULT '0' COMMENT '状态(0-成功 1-失败)',\n" +
                        "  `reason` TEXT COMMENT '原因描述',\n" +
                        "  `ctime` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '记录创建时间戳 毫秒',\n" +
                        "  `mtime` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '记录修改时间戳 毫秒',\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  KEY `idx_uid` (`uid`),\n" +
                        "  KEY `idx_tracker_id` (`tracker_id`)\n" +
                        ") ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='push推送记录表';",month, month);
//                System.out.println(sql);
            }
        }

        // push_mac_token 表
        for (int i = 0; i < num; i++) {
            sql = String.format("DROP TABLE IF EXISTS `push_mac_token_%s`;\n" +
                    "CREATE TABLE `push_mac_token_%s` (\n" +
                    "  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '自增键',\n" +
                    "  `mac` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机mac',\n" +
                    "  `reg_id` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '推送token',\n" +
                    "  `uid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '用户ID',\n" +
                    "  `ctime` BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '创建时间unix时间戳',\n" +
                    "  `mtime` BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '更新时间unix时间戳',\n" +
                    "  PRIMARY KEY (`mac`) USING BTREE,\n" +
                    "  KEY `idx_id` (`id`) USING BTREE\n" +
                    ") ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='手机设备和推送token关系表';", i ,i);
//            System.out.println(sql);
        }

        // push_user_mac 表
        for (int i = 0; i < num; i++) {
            sql = String.format("DROP TABLE IF EXISTS `push_user_mac_%s`;\n" +
                    "CREATE TABLE `push_user_mac_%s` (\n" +
                    "  `uid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '用户ID',\n" +
                    "  `mac` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '手机mac',\n" +
                    "  `ctime` BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '创建时间unix时间戳',\n" +
                    "  `mtime` BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '更新时间unix时间戳',\n" +
                    "  PRIMARY KEY (`uid`) USING BTREE\n" +
                    ") ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='uid和手机设备关系表';", i ,i);
            System.out.println(sql);
        }
    }
}

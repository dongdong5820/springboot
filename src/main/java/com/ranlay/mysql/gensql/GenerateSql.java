package com.ranlay.mysql.gensql;

/**
 * @author: Ranlay.su
 * @date: 2022-02-17 14:52
 * @description: 生成分表结构的SQL
 */
public class GenerateSql {
    public static void main(String[] args) {
//        System.out.println("USE `oppo_sns_overseas_msg`;");
//        pushHistory();
//        pushMacToken();
//        pushUserMac();

        System.out.println("USE `oppo_sns_overseas_ugc`;");
        feedbackUser();
    }

    // push_history 表
    private static void pushHistory() {
        String[] years = new String[]{"2022"};
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = String.format("%s_%s", year, i);
                String sql = String.format("DROP TABLE IF EXISTS `push_history_%s`;\n" +
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
                System.out.println(sql);
            }
        }
    }

    // push_mac_token 表
    private static void pushMacToken() {
        int num = 100;
        for (int i = 0; i < num; i++) {
            String sql = String.format("DROP TABLE IF EXISTS `push_mac_token_%s`;\n" +
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
            System.out.println(sql);
        }
    }

    // push_user_mac 表
    private static void pushUserMac() {
        int num = 100;
        for (int i = 0; i < num; i++) {
            String sql = String.format("DROP TABLE IF EXISTS `push_user_mac_%s`;\n" +
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

    // feedback_user
    private static void feedbackUser() {
        int num = 100;
        for (int i = 0; i < num; i++) {
            String sql = String.format("DROP TABLE IF EXISTS `feedback_user_%s`;\n" +
                    "CREATE TABLE `feedback_user_%s` (\n" +
                    "  `id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '主键',\n" +
                    "  `uid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '用户id',\n" +
                    "  `ssoid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '账号ssoid标识',\n" +
                    "  `source` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '发表来源，PC、WAP、ANDROID',\n" +
                    "  `model` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '发表机型',\n" +
                    "  `ip` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '发表IP',\n" +
                    "  `feedback_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '反馈系统id',\n" +
                    "  `feedback_type` INT(10) NOT NULL DEFAULT '1' COMMENT '类型(1-bug,2-建议,3-其他)',\n" +
                    "  `feedback_content` VARCHAR(1500) NOT NULL DEFAULT '' COMMENT '问题描述',\n" +
                    "  `reproduce_process` TEXT COMMENT '复现步骤',\n" +
                    "  `attachments` TEXT COMMENT '附件列表',\n" +
                    "  `device` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '机型',\n" +
                    "  `software_version` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '软件版本',\n" +
                    "  `reproduce_rate` INT(10) NOT NULL DEFAULT '0' COMMENT '复现概率(0-偶尔出现,1-仅有一次,2-经常出现,3-每次必现)',\n" +
                    "  `process_status` INT(10) NOT NULL DEFAULT '0' COMMENT '处理状态(0-已提交,1-处理中,2-解决关闭,3-无结论关闭,4-非问题关闭,5-已删除)',\n" +
                    "  `ctime` BIGINT(15) NOT NULL DEFAULT '0' COMMENT '创建时间戳',\n" +
                    "  `mtime` BIGINT(15) NOT NULL DEFAULT '0' COMMENT '最后更新时间戳',\n" +
                    "  PRIMARY KEY (`id`) USING BTREE,\n" +
                    "  KEY `idx_ssoid` (`ssoid`,`feedback_id`)\n" +
                    ") ENGINE=INNODB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户反馈';", i ,i);
            System.out.println(sql);
        }
    }
}

package com.ranlay.mysql.gensql;

/**
 * @author: Ranlay.su
 * @date: 2022-03-25 17:43
 */
public class MsgSql extends AbstractSql {
    public MsgSql(String database, String[] tableNames) {
        this.database = database;
        this.tableNames = tableNames;
    }

    public static void main(String[] args) {
        String database = "oppo_sns_overseas_msg";
        String[] tableNames = new String[]{
                "msg_system_notice",
                "msg_user_notice",
                "msg_user_system_notice_index",
                "push_mac_token",
                "push_user_mac"
        };

        MsgSql model = new MsgSql(database, tableNames);
        // 清空数据
        model.cleanUpData();
    }
}

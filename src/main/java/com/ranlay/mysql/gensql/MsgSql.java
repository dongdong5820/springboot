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
        // 清空特殊表
        MsgSql.cleanUpSpecialData();
    }

    private static void cleanUpSpecialData() {
        String[] years = new String[]{"2022"};
        String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
        // push_history 表
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = String.format("%s_%s", year, i);
                String sql = String.format("TRUNCATE TABLE `push_history_%s`;", month);
                System.out.println(sql);
            }
        }
        // push_office_message
        String sql = String.format("TRUNCATE TABLE `push_office_message`;");
        System.out.println(sql);
    }
}

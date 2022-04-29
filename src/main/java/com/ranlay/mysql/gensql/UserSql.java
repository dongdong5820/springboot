package com.ranlay.mysql.gensql;

/**
 * @author: Ranlay.su
 * @date: 2022-03-29 15:31
 */
public class UserSql extends AbstractSql {
    public UserSql(String database, String[] tableNames) {
        this.database = database;
        this.tableNames = tableNames;
    }

    public static void main(String[] args) {
        String database = "oppo_sns_overseas_user";
        String[] tableNames = new String[]{
//                "nickname_uniq",
//                "search_record",
//                "user",
//                "user_blacklist",
//                "user_color_os_uid_ssoid_index",
//                "user_count",
//                "user_pulled_blacklist",
//                "user_settings",
//                "user_ssoid_index",
//                "user_thread_index"
//                "user",
//                "user_ssoid_index",
//                "nickname_uniq",
                "user_count",
//                "user_thread_index"
        };

        UserSql model = new UserSql(database, tableNames);
        // 清空数据
        model.cleanUpData();
    }
}

package com.ranlay.mysql.gensql;

/**
 * @author: Ranlay.su
 * @date: 2022-03-25 17:43
 */
public class ChatSql extends AbstractSql {
    public ChatSql(String database, String[] tableNames) {
        this.database = database;
        this.tableNames = tableNames;
    }

    public static void main(String[] args) {
        String database = "oppo_sns_overseas_chat";
        String[] tableNames = new String[]{
                "chat_group",
                "chat_group_to_user_index",
                "chat_msg",
                "chat_private_group",
                "chat_user_msg_index",
                "chat_user_to_group_index"
        };

        ChatSql model = new ChatSql(database, tableNames);
        // 清空数据
        model.cleanUpData();
    }
}

package com.ranlay.enums;

import java.util.Objects;

/**
 * @author: Ranlay.su
 * @date: 2022-03-19 15:58
 * @description: push 枚举
 * @version: 1.0.0
 */
public class PushMsgEnum {

    /**
     * push消息类型
     */
    public enum Type {
        NOTIFICATION_MESSAGE(1, "通知栏消息"),
        DATA_MESSAGE(2, "透传消息"),
        ;

        Type(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private Integer code;
        private String desc;

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        /**
         * 获取枚举
         *
         * @param code
         * @return
         */
        public static Type of(Integer code) {
            if (Objects.isNull(code)) {
                return null;
            }
            for (Type enums : Type.values()) {
                if (enums.getCode().equals(code)) {
                    return enums;
                }
            }
            return null;
        }
    }

    /**
     * push 配置
     */
    public enum PushConfig {
        /**
         * 主贴评论
         */
        NEW_COMMENT(
                "",
                "206.com just commented on your thread!",
                "/app/thread?threadId=1025399750682214407",
                "New_comment",
                "1000",
                Type.NOTIFICATION_MESSAGE
        ),
        /**
         * 回复
         */
        NEW_REPLY(
                "",
                "206.com just replied to you!",
                "/app/comment?replyId1014727808363003905&commentId=1014699507087048709",
                "New_reply",
                "1000",
                Type.NOTIFICATION_MESSAGE
        ),
        /**
         * 新粉丝
         */
        NEW_FOLLOWER(
                "",
                "206.com just followed you!",
                "/app/user?userId=1345690597",
                "New_follower",
                "1000",
                Type.NOTIFICATION_MESSAGE
        ),
        /**
         * 帖子点赞
         */
        NEW_THREAD_LIKE(
                "",
                "206.com just liked your thread!",
                "/app/thread?threadId=1025399750682214407",
                "New_thread_like",
                "1000",
                Type.NOTIFICATION_MESSAGE
        ),
        /**
         * 在帖子@人
         */
        NEW_THREAD_AT(
                "",
                "206.com tagged you in the thread!",
                "/app/thread?threadId=1025399750682214407",
                "New_thread_tag",
                "1000",
                Type.NOTIFICATION_MESSAGE
        ),
        /**
         * 在评论@人
         */
        NEW_COMMENT_AT(
                "",
                "206.com tagged you in the comment!",
                "/app/comment?threadId=1014699158750101506&commentId=1014699507087048709",
                "New_comment_tag",
                "1000",
                Type.NOTIFICATION_MESSAGE
        ),
        /**
         * 在回复@人
         */
        NEW_REPLY_AT(
                "",
                "206.com tagged you in the reply!",
                "/app/comment?replyId1014727808363003905&commentId=1014699507087048709",
                "New_reply_tag",
                "1000",
                Type.NOTIFICATION_MESSAGE
        ),

        /**
         * 私信
         */
        NEW_PRIVATE_CHAT(
                "",
                "206.com sent you a message!",
                "/app/message/chat?receiverUid=54",
                "New_private_chat",
                "1001",
                Type.NOTIFICATION_MESSAGE
        ),
        /**
         * 官方消息
         */
        OFFICIAL_NOTICE(
                "",
                "4444",
                "https://forums.oneplus.com/app/thread?threadId=1027003998561894402",
                "official_25",
                "1002",
                Type.NOTIFICATION_MESSAGE
        ),
        /**
         * 通知客户端刷新消息小红点
         */
        NOTICE_FLUSH(
                "",
                "",
                "",
                "",
                "2000",
                Type.DATA_MESSAGE
        ),
        ;

        PushConfig(String title, String content, String link, String trackerId, String clientAction, Type type) {
            this.title = title;
            this.content = content;
            this.link = link;
            this.trackerId = trackerId;
            this.clientAction = clientAction;
            this.type = type;
        }

        private String title;
        private String content;
        private String link;
        private String trackerId;
        /**
         * 客户端行为: 1000-push通知 1001-私信通知 1002-官方消息 2000-刷新消息小红点
         */
        private String clientAction;
        private Type type;

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getLink() {
            return link;
        }

        public String getTrackerId() {
            return trackerId;
        }

        public String getClientAction() {
            return clientAction;
        }

        public Type getType() {
            return type;
        }
    }
}

package com.ranlay.core.model.req.fcm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Ranlay.su
 * @date: 2021-11-02 9:55
 * @description: FCM推送消息请求实体
 * @version: 1.0.0
 */
@NoArgsConstructor
@Data
public class FCMSendMessageReqDTO implements Serializable {
    private static final long serialVersionUID = 5809928752971252836L;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String body;

    /**
     * 用户token,多个用英文逗号','隔开
     */
    private String tokens;

    /**
     * 主题
     */
    private String topic;
}

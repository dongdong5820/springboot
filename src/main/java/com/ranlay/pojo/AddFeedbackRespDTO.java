package com.ranlay.pojo;

import java.io.Serializable;

/**
 * @author: Ranlay.su
 * @date: 2022-07-13 15:45
 * @description: 提交反馈响应
 * @version: 1.0.0
 */
public class AddFeedbackRespDTO implements Serializable {
    private static final long serialVersionUID = 4571976750928670877L;

    /**
     * 反馈ID
     */
    private Long feedbackId;

    /**
     * 处理状态
     */
    private Integer processStatus;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String toString() {
        return "AddFeedbackRespDTO{" +
                "feedbackId=" + feedbackId +
                ", processStatus=" + processStatus +
                '}';
    }
}

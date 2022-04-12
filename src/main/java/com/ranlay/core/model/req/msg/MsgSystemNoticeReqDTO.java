package com.ranlay.core.model.req.msg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author: Ranlay.su
 * @date: 2022-04-06 14:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class MsgSystemNoticeReqDTO implements Serializable {
    private static final long serialVersionUID = -3465122842039015831L;

    private Integer type;
    private String jumpUrl;
    private Long contentId;
}

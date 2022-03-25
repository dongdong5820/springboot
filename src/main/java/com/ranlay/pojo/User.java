package com.ranlay.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Ranlay.su
 * @date: 2022-03-25 17:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User implements Serializable {

    private static final long serialVersionUID = 8692711653058729243L;

    private Long uid;

    @JSONField(name = "user_name")
    private String userName;

    private Integer height;

    @JSONField(format = "yyyy-MM-dd")
    private Date age;

//    @JSONField(jsonDirect = true)
    private String value;
}

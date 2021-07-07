package com.ranlay.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Ranlay.su
 * @date: 2021-07-06 15:48
 * @description: 员工pojo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;
    private String username;
    private String password;
    private int sex;
    private int birthday;
    private String birthdayStr;
}

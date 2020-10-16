package com.cssl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    //用户编号
    private Long userid;

    //用户名
    private String name;

    //密码
    private String password;

    //用户角色 1.管理员 2.普通用户
    private Integer status;
}

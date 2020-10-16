package com.cssl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item implements Serializable {
    //编号
    private Long iid;

    //用户编号
    private Long uid;

    //主题编号
    private Long sid;

    //选项编号
    private Long oid;
}

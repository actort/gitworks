package com.cssl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Option implements Serializable {

    //选项编号
    private Long oid;

    //主题编号
    private Long sid;

    //选项名
    private String option;

    //投票数量
    private Integer order;
}

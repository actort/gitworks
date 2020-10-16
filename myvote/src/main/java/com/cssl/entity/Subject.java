package com.cssl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subject implements Serializable {

    //主题编号
    private Long sid;

    //主题内容
    private String title;

    //选项类型  1.单选 2.多选
    private Integer type;

    //持有选项引用
    private List<Option> listo;
}

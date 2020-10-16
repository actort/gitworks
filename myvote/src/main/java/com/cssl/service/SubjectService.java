package com.cssl.service;

import com.cssl.entity.Subject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SubjectService {

    public PageInfo<Subject> fenye(int index, int size);

    //根据主题编号查询主题信息和选项信息
    public Subject getSubBySid(Long sid);

    //添加主题信息和选性信息
    public boolean addSubAndOption(Subject subject,List<String> options);

    //根据主题编号删除主题信息和选项信息
    public boolean delSubAndOptionBySid(Long sid);

    //根据主题编号修改主题信息和选项信息
    public boolean updSubAndOptionBySid(Subject sub, List<Long> oids, List<String> options);
}

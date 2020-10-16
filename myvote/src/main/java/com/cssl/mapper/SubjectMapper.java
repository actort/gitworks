package com.cssl.mapper;

import com.cssl.entity.Subject;

import java.util.List;

public interface SubjectMapper {
    //查询分页投票信息
    public List<Subject> fenye();

    //根据主题编号查询主题信息和选项信息
    public Subject getSubBySid(Long sid);

    //查询当前自增值
    public Long getAutoValue();

    //添加主题信息
    public int addSub(Subject subject);

    //根据主题编号删除主题信息
    public int delSubBySid(Long sid);

    //根据主题编号修改主题信息
    public int updSubBySid(Subject sub);
}

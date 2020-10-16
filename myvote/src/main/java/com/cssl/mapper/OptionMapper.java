package com.cssl.mapper;

import com.cssl.entity.Option;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionMapper {

    //根据主题编号查询选项信息
    public List<Option> getOptionBySid(Long sid);

    //新增选项信息
    public int addOption(Option option);

    //根据选项编号删除选项信息
    //public int delOptionByOid(Long oid);

    //根据主题编号删除选项信息并排除某些不需要删除的
    public int delOptionBySid(Long sid, @Param("list") List<Long> oids);

    //根据选项编号修改选项信息
    public int updOptionByOid(Option option);

    //根据选项编号添加投票数量
    public int updOptionOrderByOid(Long oid);
}

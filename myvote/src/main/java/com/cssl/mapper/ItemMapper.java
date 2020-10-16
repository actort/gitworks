package com.cssl.mapper;

import com.cssl.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper {

    //得到主题投票总数
    public int getItemBySidCount(Long sid);

    //得到主题的用户投票人数
    public int getUserCountBySid(Long sid);

    //根据主题编号删除投票信息,并排除不需要删除的选项信息
    public int delItemBySid(Long sid, @Param("list") List<Long> oids);

    //根据用户编号和主题编号查询是否存在投票信息
    public int getItemByUidAndSid(Long uid, Long sid);

    //添加投票信息
    public int addItem(Item item);
}

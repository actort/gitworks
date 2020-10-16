package com.cssl.service;

import java.util.List;

public interface ItemService {

    //得到主题投票总数
    public int getItemBySidCount(Long sid);

    //得到主题的用户投票人数
    public int getUserCountBySid(Long sid);

    //添加投票信息
    public boolean addItemAndOption(Long uid, Long sid, List<Long> oids);
}

package com.cssl.service.impl;

import com.cssl.entity.Item;
import com.cssl.mapper.ItemMapper;
import com.cssl.mapper.OptionMapper;
import com.cssl.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public int getItemBySidCount(Long sid) {
        return itemMapper.getItemBySidCount(sid);
    }

    @Override
    public int getUserCountBySid(Long sid) {
        return itemMapper.getUserCountBySid(sid);
    }

    @Override
    public boolean addItemAndOption(Long uid, Long sid, List<Long> oids) {
        try {
            int itemByUidAndSid = itemMapper.getItemByUidAndSid(uid, sid);
            //判断是否存在投票信息
            if (itemByUidAndSid == 0) {
                //不存在，循环添加投票信息
                for (Long oid : oids) {
                    //创建item对象并赋值
                    Item item = new Item();
                    item.setUid(uid);
                    item.setSid(sid);
                    item.setOid(oid);
                    boolean flag1 = itemMapper.addItem(item) > 0;
                    boolean flag2 = optionMapper.updOptionOrderByOid(oid) > 0;
                    //判断
                    if (!flag1 || !flag2)
                        throw new Exception("投票正忙...");
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//事务回滚
        }
        return false;
    }
}

package com.cssl.service.impl;

import com.cssl.entity.Option;
import com.cssl.entity.Subject;
import com.cssl.mapper.ItemMapper;
import com.cssl.mapper.OptionMapper;
import com.cssl.mapper.SubjectMapper;
import com.cssl.service.SubjectService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private OptionMapper optionMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public PageInfo<Subject> fenye(int index, int size) {
        Page<Subject> page = PageHelper.startPage(index, size);
        List<Subject> fenye = subjectMapper.fenye();
        return page.toPageInfo();
    }

    @Override
    public Subject getSubBySid(Long sid) {
        return subjectMapper.getSubBySid(sid);
    }

    @Override
    @Transactional
    public boolean addSubAndOption(Subject subject, List<String> options) {
        try {
            //查询主题自增值
            Long autoValue = subjectMapper.getAutoValue();
            subject.setSid(autoValue);
            subjectMapper.addSub(subject);
            for (String str : options) {
                System.out.println("str:" + str);
                System.out.println("".equals(str));
                //非空验证
                if (str != null && !"".equals(str)) {
                    //创建Option对象并赋值
                    Option option = new Option();
                    option.setSid(autoValue);
                    option.setOption(str);
                    //添加选项信息
                    optionMapper.addOption(option);
                }
            }
            //返回主题添加结果
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delSubAndOptionBySid(Long sid) {
        try {
            itemMapper.delItemBySid(sid, null);//保存投票信息删除结果
            optionMapper.delOptionBySid(sid, null);//保存选项删除结果
            //判断主题删除结果
            if (subjectMapper.delSubBySid(sid) > 0)
                return true;
            else
                throw new Exception("主题和选项删除暂不可用");
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updSubAndOptionBySid(Subject sub, List<Long> oids, List<String> options) {
        try {
            //判断oid的值多，还是option得值多
            int length = options.size() >= oids.size() ? options.size() : oids.size();
            List<Long> oidss = new ArrayList<Long>();//用来存储oid值
            for (int i = 0; i < length; i++) {
                //创建Option对象
                Option option = new Option();
                option.setOption(options.get(i));
                //当循环处于存在oid时是修改，不存在时是新增
                if (i < oids.size()) {
                    Long oid = oids.get(i);
                    option.setOid(oid);
                    oidss.add(oid);
                    optionMapper.updOptionByOid(option);
                    //判断是否最后一个oid,进行删除
                    if (i == (oids.size() - 1)) {
                        //删除主题下oidss集合不存在oid的选项信息前，先删除该选项的投票纪录
                        itemMapper.delItemBySid(sub.getSid(), oidss);
                        //删除主题下oidss集合不存在oid的选项信息
                        optionMapper.delOptionBySid(sub.getSid(), oidss);
                    }
                } else {
                    //添加前将主题编号赋值给option对象
                    option.setSid(sub.getSid());
                    optionMapper.addOption(option);
                }
            }
            //更新主题的选项类型
            subjectMapper.updSubBySid(sub);
            //到这还没报错，就算维护成功了
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}

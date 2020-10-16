package com.cssl.mapper;

import com.cssl.entity.User;

public interface UserMapper {
    //用户登录
    public User login(User user);

    //用户注册
    public int register(User user);

    //根据用户名查询用户信息
    public int getUserByUname(String name);
}

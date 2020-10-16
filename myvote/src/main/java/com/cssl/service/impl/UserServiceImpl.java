package com.cssl.service.impl;

import com.cssl.entity.User;
import com.cssl.mapper.UserMapper;
import com.cssl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public boolean getUserByUname(String name) {
        return userMapper.getUserByUname(name) > 0;
    }

    @Override
    public boolean register(User user) {
        return userMapper.register(user) > 0;
    }
}

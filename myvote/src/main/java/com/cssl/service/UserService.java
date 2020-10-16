package com.cssl.service;

import com.cssl.entity.User;

public interface UserService {

    public User login(User user);

    public boolean register(User user);

    public boolean getUserByUname(String name);
}

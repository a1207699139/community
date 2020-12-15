package com.chenqi.community.service;

import com.chenqi.community.entity.User;

import java.util.List;

public interface UserService {
    //查找所有用户
    List<User> findAllUser();

    //登陆
    User login(String username, String password);

    //注册用户
    void save(User user);
}

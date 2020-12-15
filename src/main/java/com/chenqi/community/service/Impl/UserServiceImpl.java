package com.chenqi.community.service.Impl;

import com.chenqi.community.dao.UserDao;
import com.chenqi.community.entity.User;
import com.chenqi.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ardai
 */
@Service
@Transactional(rollbackFor = {RuntimeException.class})
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public User login(String username, String password) {
        User user = userDao.findUserByUsername(username);
        if (user!=null){
            if (user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {RuntimeException.class})
    public void save(User user) {
        user.setSign("这是一段有趣的签名~");
        userDao.insertOneUser(user);
    }
}

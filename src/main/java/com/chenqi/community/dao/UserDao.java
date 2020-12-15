package com.chenqi.community.dao;

import com.chenqi.community.entity.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ardai
 */
@Repository
@MapperScan
public interface UserDao {
    //查找所有用户
    List<User> findAllUser();

    //根据用户名查找一个用户
    User findUserByUsername(@Param("username") String username);

    //插入一个用户
    void insertOneUser(User user);
}

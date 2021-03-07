package com.twiss.springboot.dao.impl;

import com.twiss.springboot.dao.UserDAO;
import com.twiss.springboot.domain.User;
import com.twiss.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Twiss
 * @Date: 2021/3/6 1:19 下午
 */
@Repository
public class UserDAOImpl implements UserDAO {
    /**
     * 用户管理模块的mapper组件
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }

    @Override
    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public Long saveUser(User user) {
        userMapper.saveUser(user);
        return user.getId();
    }

    @Override
    public Boolean updateUser(User user) {
        userMapper.updateUser(user);
        return true;
    }

    @Override
    public Boolean removeUser(Long userId) {
        userMapper.removeUser(userId);
        return true;
    }
}

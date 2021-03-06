package com.twiss.springboot.service.impl;

import com.twiss.springboot.dao.UserDAO;
import com.twiss.springboot.domain.User;
import com.twiss.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Twiss
 * @Date: 2021/3/6 1:30 下午
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> listUsers() {
        return userDAO.listUsers();
    }


    @Override
    public User getUserById(Long userId) {
        return userDAO.getUserById(userId);
    }

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void removeUser(Long userId) {
        userDAO.removeUser(userId);
    }
}

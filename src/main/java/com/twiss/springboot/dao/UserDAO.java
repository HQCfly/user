package com.twiss.springboot.dao;

import com.twiss.springboot.domain.User;

import java.util.List;

/**
 * @Author: Twiss
 * @Date: 2021/3/6 1:17 下午
 */
public interface UserDAO {
    List<User> listUsers();

    User getUserById(Long userId);
    void saveUser(User user);

    void updateUser(User user);

    void removeUser(Long userId);

}

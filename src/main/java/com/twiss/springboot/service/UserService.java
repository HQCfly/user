package com.twiss.springboot.service;

import com.twiss.springboot.domain.User;

import java.util.List;

/**
 * @Author: Twiss
 * @Date: 2021/3/6 1:24 下午
 */
public interface UserService {
    List<User> listUsers();

    User getUserById(Long userId);
    void saveUser(User user);

    void updateUser(User user);

    void removeUser(Long userId);
}

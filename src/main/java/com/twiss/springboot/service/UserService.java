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
    Long saveUser(User user);

    Boolean updateUser(User user);

    Boolean removeUser(Long userId);
}

package com.twiss.springboot.dao;

import com.twiss.springboot.domain.User;
import com.twiss.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @Author: Twiss
 * @Date: 2021/3/7 5:54 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class UserDAOImplTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void testListUser(){
        User user = new User();
        user.setAge(22);
        user.setName("Evil");
        long resultUserId = userDAO.saveUser(user);

        List<User> userList = new ArrayList<User>();
        userList.add(user);

        List<User> resultUsers = userDAO.listUsers();

        assertEquals(userList.size(),resultUsers.size());

        Boolean removeResult = userDAO.removeUser(resultUserId);
        assertTrue(removeResult);
    }

    @Test
    public void testAddUser(){
        User user = new User();
        user.setAge(22);
        user.setName("Evil");
        Long resultUserId = userDAO.saveUser(user);

        assertThat(resultUserId,is(greaterThan(0L)));

        Boolean removeResult = userDAO.removeUser(resultUserId);
        assertTrue(removeResult);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setAge(22);
        user.setName("Evil");
        Long resultUserId = userDAO.saveUser(user);

        user.setAge(21);
        Boolean updateResult = userDAO.updateUser(user);
        assertTrue(updateResult);

        User updateUser = userDAO.getUserById(user.getId());
        assertEquals("21",updateUser.getAge().toString());

        Boolean removeResult = userDAO.removeUser(resultUserId);
        assertTrue(removeResult);
    }

    @Test
    public void testRemoveUser(){
        User user = new User();
        user.setAge(22);
        user.setName("Evil");
        Long resultUserId = userDAO.saveUser(user);
        Boolean removeResult = userDAO.removeUser(resultUserId);
        assertTrue(removeResult);
    }
}

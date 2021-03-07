package com.twiss.springboot.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import com.twiss.springboot.dao.UserDAO;
import com.twiss.springboot.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Twiss
 * @Date: 2021/3/7 2:50 下午
 * @RunWith 这个注解是在执行单元测试的时候，不是直接去执行里面的单元测试的方法
 * 因为那些方法执行之前，是需要做一些准备工作，它需要先初始化一个Spring容器的，
 * 所以得找这个SpringRunning这个类，来先准备Spring容器，在执行各个测试方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    /**
     * @MockBean 的注解表示这个UserDAO就不会使用我们定义的那个userDAO，
     * 整理室友SpringBoot整个的mockito框架，然后实现一个userDAO匿名实现类
     * 然后将模拟出来实现了UserDAO接口的类实例bean，放入spring容器中
     * 替代我们定义的UserDAO
     */
    @MockBean
    private UserDAO userDAO;

    /**
     * 测试用例：查询所有的用户信息
     */
    @Test
    public void testListUser(){
        List<User> userList = new ArrayList<User>();
        User user = new User();
        user.setId(31L);
        user.setName("Olive");
        user.setAge(26);
        userList.add(user);

        // 对useDAO进行mock逻辑配置
        when(userDAO.listUsers()).thenReturn(userList);

        // 测试UserService的listUsers方法
        List<User> resultUsers = userService.listUsers();

        // 对测试结果进行断言
        assertEquals(1,resultUsers.size());
        assertEquals(user,resultUsers.get(0));

    }

    /**
     * 测试用例: 根据ID查询一个用户
     */
    @Test
    public void testGetUserById(){
        // 准备模拟数据
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("测试用户");
        user.setAge(20);

        // 对UserDAO进行mock
        when(userDAO.getUserById(userId)).thenReturn(user);

        User resultUser = userService.getUserById(userId);

        assertEquals(user,resultUser);
    }

    /**
     * 测试用例: 新增一个用户
     */
    @Test
    public void testSaveUser(){
        // 准备模拟数据
        Long userId = 1L;
        User user = new User();
        user.setName("测试用户");
        user.setAge(20);

        // 对UserDAO进行mock
        when(userDAO.saveUser(user)).thenReturn(userId);

        Long resultUserId = userService.saveUser(user);

        assertEquals(userId,resultUserId);
    }

    /**
     * 测试用例: 修改用户
     */
    @Test
    public void testUpdateUser(){
        // 准备模拟数据
        Long userId = 1L;
        User user = new User();
        user.setName("测试用户");
        user.setAge(20);

        // 对UserDAO进行mock
        when(userDAO.updateUser(user)).thenReturn(true);

        boolean result = userService.updateUser(user);

        assertTrue(result);
    }

    /**
     * 测试用例: 删除用户
     */
    @Test
    public void testRemoveUser(){
        // 准备模拟数据
        Long userId = 1L;

        // 对UserDAO进行mock
        when(userDAO.removeUser(userId)).thenReturn(true);

        boolean result = userService.removeUser(userId);

        assertTrue(result);
    }

}

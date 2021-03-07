package com.twiss.springboot.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twiss.springboot.domain.User;
import com.twiss.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @Author: Twiss
 * @Date: 2021/3/7 3:55 下午
 */
@RunWith(SpringRunner.class)
/**
 * 通过这个注解，表明你要测试的controller是谁
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {
    /**
     * 注入一个Mock，模拟对controller发起HTTP请求
     */
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    /**
     * 测试用例: list所有用户
     */
    @Test
    public void testListUsers() {
        try {
            // 准备好mock userDAO的返回数据
            List<User> users = new ArrayList<User>();
            User user = new User();
            user.setId(1L);
            user.setName("测试用户");
            user.setAge(20);

            // 对userDAO进行mock逻辑设置
            when(userService.listUsers()).thenReturn(users);
            mockMvc.perform(get("/user/"))
                    .andExpect(content().json(JSONArray.toJSONString(users)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 测试用例: 根据ID查询
     */
    @Test
    public void testGetUserById() {
        try {
            // 准备好mock userDAO的返回数据
            Long userId = 1L;
            User user = new User();
            user.setId(1L);
            user.setName("测试用户");
            user.setAge(20);

            // 对userDAO进行mock逻辑设置
            when(userService.getUserById(userId)).thenReturn(user);
            mockMvc.perform(get("/user/{id}",userId))
                    .andExpect(content().json(JSONArray.toJSONString(user)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 测试用例: 新增用户
     */
    @Test
    public void testSaveUser() {
        try {
            // 准备好mock userDAO的返回数据
            Long userId = 1L;
            User user = new User();
            user.setName("测试用户");
            user.setAge(20);

            // 对userDAO进行mock逻辑设置
            when(userService.saveUser(user)).thenReturn(userId);
            mockMvc.perform(post("/user/").contentType("application/json").content(JSONObject.toJSONString(user)))
                    .andExpect(content().json("{'status':'success','message':'新增用户Id为"+user.getId()+"'}"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 测试用例: 修改用户
     */
    @Test
    public void testUpdateUser() {
        try {
            // 准备好mock userDAO的返回数据
            Long userId = 1L;
            User user = new User();
            user.setId(1L);
            user.setName("测试用户");
            user.setAge(20);

            // 对userDAO进行mock逻辑设置
            when(userService.updateUser(user)).thenReturn(true);
            mockMvc.perform(put("/user/{id}",userId).contentType("application/json").content(JSONObject.toJSONString(user)))
                    .andExpect(content().string("success"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 测试用例: 删除用户
     */
    @Test
    public void testRemoveUser() {
        try {
            // 准备好mock userDAO的返回数据
            Long userId = 1L;

            // 对userDAO进行mock逻辑设置
            when(userService.removeUser(userId)).thenReturn(true);
            mockMvc.perform(delete("/user/{id}",userId))
                    .andExpect(content().string("success"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

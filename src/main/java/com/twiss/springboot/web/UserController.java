package com.twiss.springboot.web;

import com.twiss.springboot.domain.User;
import com.twiss.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Twiss
 * @Date: 2021/3/6 1:33 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // GET请求代表着是查询数据
    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<User> listUsers() {
        return userService.listUsers();
    }

    // GET请求+{id}代表的是查询某个用户
    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    // POST请求代表着是新增数据
    @PostMapping("/")
    public String saveUser(@RequestBody @Validated({User.Save.class}) User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            FieldError fieldError = (FieldError) errors.get(0);
            String message = fieldError.getObjectName()+","+fieldError.getField()
                    + ","+fieldError.getDefaultMessage();
            return "{'status':'error','message':'"+message+"'}";
        }
        userService.saveUser(user);
        return "{'status':'success','message':'新增用户Id为"+user.getId()+"'}";
    }

    // PUT请求代表的是更新
    /**
    @RequestMapping(value="/{userId}", method=RequestMethod.PUT)
    public String updateUser(@PathVariable("userId") Long userId, User user) {
        System.out.println(userId);
        System.out.println(user);
        user.setId(userId);
        userService.updateUser(user);
        return "success";
    }
     */
    @PutMapping("/{id}")
    public String updateUser(@RequestBody User user){
        userService.updateUser(user);
        return "success";
    }

    // DELETE请求代表的是删除
    @RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable("userId") Long userId) {
        userService.removeUser(userId);
        return "success";
    }

}

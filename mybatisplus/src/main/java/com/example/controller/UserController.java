package com.example.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.model.auto.User;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Alex
 * @since 2021-04-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/getUser")
    public User getUser(){
        return userService.getById(1);
    }

    @PostMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @PostMapping("/page")
    public IPage<User> page(){
        return userService.selectPage();
    }
}

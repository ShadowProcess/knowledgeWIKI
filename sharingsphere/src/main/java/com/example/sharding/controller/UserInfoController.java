package com.example.sharding.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sharding.bean.BaseEntity;
import com.example.sharding.bean.UserInfo;
import com.example.sharding.service.UserInfoService;
import com.example.sharding.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "/api", tags = {"用户信息表接口"})
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/listUser")
    @ApiOperation(value = "获取所有用户")
    public Result<List<UserInfo>> listUser() {
        List<UserInfo> areas = userInfoService.list();
        System.out.println("获取所有用户:"+areas);
        return Result.<List<UserInfo>>builder().success(100).data(areas).build();
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户")
    public Result<String> addUser(@RequestParam Integer addCount) {
        String response = userInfoService.addUser(addCount);
        return Result.<String>builder().success(100).data(response).build();
    }

    @GetMapping("/pageUser")
    @ApiOperation(value = "用户分页")
    public Result<IPage<UserInfo>> pageUser(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize ) {
        IPage<UserInfo> page = userInfoService.page(new Page<>(pageNum, pageSize,true), Wrappers.<UserInfo>lambdaQuery().orderByAsc(BaseEntity::getId));
        return Result.<IPage<UserInfo>>builder().success(100).data(page).build();
    }
}

package com.example.sharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sharding.bean.UserInfo;

public interface UserInfoService extends IService<UserInfo> {
    String addUser(Integer addCount);
}

package com.example.sharding.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sharding.bean.UserInfo;
import com.example.sharding.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String addUser(Integer addCount) {
        if (addCount <= 0) {
            addCount = 10;
        }
        for (int i = 1; i <= addCount; i++) {
            UserInfo user = new UserInfo();
            user.setDeleted(0);
            user.setUserName(UUID.randomUUID().toString() + i);
            user.setPhone("phone-" + (i));
            user.setPassword("password" + i);
            super.save(user);
        }
        return "添加成功";
    }
}

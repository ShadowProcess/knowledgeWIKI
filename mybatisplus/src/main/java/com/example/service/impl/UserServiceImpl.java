package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.model.auto.User;
import com.example.mapper.auto.UserMapper;
import com.example.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2021-04-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }


    //mybatis-plus分页
    @Override
    public IPage<User> selectPage() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(User::getUserName, "k");

        //// 不查询总记录数 page.setSearchCount(false);
        Page<User> userPage = new Page<>(1, 1, true);
        IPage<User> userIPage = userMapper.selectPage(userPage, userLambdaQueryWrapper);
        System.out.println("总页数： " + userIPage.getPages());
        System.out.println("总记录数： " + userIPage.getTotal());
        userIPage.getRecords()
                .forEach(System.out::println);
        return userIPage;
    }

}

package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.model.auto.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alex
 * @since 2021-04-03
 */
public interface IUserService extends IService<User> {

    //添加自定义方法
    public List<User> findAllUser();

    //测试分页
    public IPage<User> selectPage();
}

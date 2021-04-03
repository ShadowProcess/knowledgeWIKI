package com.example.mapper.auto;

import com.example.model.auto.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alex
 * @since 2021-04-03
 */
public interface UserMapper extends BaseMapper<User> {

    //添加自定义方法，使用自定义mapper
    public List<User> findAllUser();
}

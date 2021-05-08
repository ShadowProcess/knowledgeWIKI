package com.example.multidatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.multidatasource.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 注意：“@DS”注解请使用在方法上面，在类上面使用会出问题，现在的例子是之前的版本，新版本在Mapper上面使用会出现异常。
 */
@DS(value = "a01")
@Mapper
public interface MapperA01 extends BaseMapper<UserInfo> {
    @Select("SELECT * FROM user_info")
    public List<UserInfo> getAllUser();
}
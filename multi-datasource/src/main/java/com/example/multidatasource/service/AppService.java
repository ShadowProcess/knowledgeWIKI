package com.example.multidatasource.service;

import com.example.multidatasource.bean.UserInfo;
import com.example.multidatasource.mapper.MapperA01;
import com.example.multidatasource.mapper.MapperA02;
import com.example.multidatasource.mapper.MapperA03;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private MapperA01 mapperA01;

    @Autowired
    private MapperA02 mapperA02;

    @Autowired
    private MapperA03 mapperA03;

    /**
     * 查询A01库中的数据
     *
     * @return 用户信息列表
     */
    public List<UserInfo> getUser01() {
        return mapperA01.getAllUser();
    }

    /**
     * 查询A02库中的数据
     *
     * @return 用户信息列表
     */
    public List<UserInfo> getUser02() {
        return mapperA02.getAllUser();
    }


    /**
     * 查询A03库中的数据
     *
     * @return 用户信息列表
     */
    public List<UserInfo> getUser03() {
        return mapperA03.getAllUser();
    }
}
package com.example.demo.basecommonmapper;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 通用Mapper，该Mapper已经封装了数据表的基本操作，
 * 如果需要更复杂和详细的控制，那么自己在对应Mapper书写对应方法和对应xml的编写
 * @param <T>
 */

public interface BaseCommonMapper<T> extends Mapper<T>, ConditionMapper<T>, InsertListMapper<T> {
}

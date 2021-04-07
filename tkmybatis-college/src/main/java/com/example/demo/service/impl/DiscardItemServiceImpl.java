package com.example.demo.service.impl;

import com.example.demo.mapper.DiscarditemMapper;
import com.example.demo.model.Discarditem;
import com.example.demo.service.DiscardItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class DiscardItemServiceImpl implements DiscardItemService {
    /**
     *  required=false 表示可以是null，虽然它不是空，是为了解决idea提示红线的问题，
     *  因为这个mapper具体实现是mybatis的，idea处理不了
     */
    private final DiscarditemMapper discarditemMapper;

    @Autowired(required = false)
    public DiscardItemServiceImpl(DiscarditemMapper discarditemMapper) {
        this.discarditemMapper = discarditemMapper;
    }

    @Override
    public PageInfo<Discarditem> page(int pageNum, int pageSize) {
        /**
         * mybatis的example用于构建条件使用
         */
        Example example = new Example(Discarditem.class);
        //PageHelper.startPage(pageNum,pageSize);
        PageHelper.offsetPage(pageNum,pageSize);
        PageInfo<Discarditem> pageInfo = new PageInfo<>(discarditemMapper.selectByExample(example));
        return pageInfo;
    }
}

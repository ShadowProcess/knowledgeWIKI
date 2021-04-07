package com.example.demo.service;

import com.example.demo.model.Discarditem;
import com.github.pagehelper.PageInfo;

public interface DiscardItemService {

    /**
     * 使用分页插件，pageHelper
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Discarditem> page(int pageNum, int pageSize);
}

package com.example.demo.service;

import com.example.demo.model.Version;

public interface VersionService {

    void save(Version version);

    /**
     * 自定义映射处理
     */
    Version test(Integer id);
}

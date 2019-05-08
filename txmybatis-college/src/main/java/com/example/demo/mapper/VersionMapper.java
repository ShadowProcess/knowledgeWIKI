package com.example.demo.mapper;

import com.example.demo.basecommonmapper.BaseCommonMapper;
import com.example.demo.model.Version;

public interface VersionMapper extends BaseCommonMapper<Version> {

    Version test(Integer id);

}
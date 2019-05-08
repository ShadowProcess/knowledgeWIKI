package com.example.demo.service.impl;

import com.example.demo.mapper.VersionMapper;
import com.example.demo.model.Version;
import com.example.demo.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl implements VersionService {

    private final VersionMapper versionMapper;
    @Autowired(required = false)
    public VersionServiceImpl(VersionMapper versionMapper) {
        this.versionMapper = versionMapper;
    }

    @Override
    public void save(Version version) {
        versionMapper.insert(version);
    }

    @Override
    public Version test(Integer id) {
        return versionMapper.test(id);
    }
}

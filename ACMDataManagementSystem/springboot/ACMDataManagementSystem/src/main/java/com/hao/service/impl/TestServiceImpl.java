package com.hao.service.impl;

import com.hao.mapper.TestMapper;
import com.hao.pojo.Test;
import com.hao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> listTest() {
        return testMapper.listTest();
    }
}

package com.hao.service.impl;

import com.hao.mapper.DataMapper;
import com.hao.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public List<Integer> getScore(Integer id) {
        return dataMapper.getScore(id);
    }
}

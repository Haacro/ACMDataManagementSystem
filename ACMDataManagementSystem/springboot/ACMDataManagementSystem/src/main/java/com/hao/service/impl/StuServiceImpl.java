package com.hao.service.impl;

import com.hao.mapper.StuMapper;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public List<Stu> getAll() {
        return stuMapper.getAll();
    }

    @Override
    public void deleteById(Integer id) {
        stuMapper.deleteById(id);
    }

    @Override
    public void add(Stu stu) {
        stu.setCreateTime(LocalDateTime.now());
        stu.setUpdateTime(LocalDateTime.now());

        stuMapper.insert(stu);
    }
}

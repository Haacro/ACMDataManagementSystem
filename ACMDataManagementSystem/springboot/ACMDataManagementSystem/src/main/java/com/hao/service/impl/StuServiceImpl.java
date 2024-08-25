package com.hao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hao.mapper.StuMapper;
import com.hao.pojo.PageBean;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    /*@Override
    public List<Stu> getAll() {
        return stuMapper.getAll();
    }*/

    /*@Override
    public void deleteById(Integer id) {
        stuMapper.deleteById(id);
    }*/

    @Override
    public Stu findByStuNo(String stuNo) {
        Stu s = stuMapper.findByStuNo(stuNo);
        return s;
    }

    @Override
    public void register(Stu stu) {
        stu.setPassword(DigestUtils.md5Hex(stu.getPassword()));
        stuMapper.insert(stu);
    }

    @Override
    public void add(Stu stu) {
        stu.setCreateTime(LocalDateTime.now());
        stu.setUpdateTime(LocalDateTime.now());
        stuMapper.insert(stu);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String stuNo, String stuName,
                         String className, Short gender, String school, Integer stuScore) {
        //1. 设置分页参数
        PageHelper.startPage(page, pageSize);

        //2. 执行查询
        List<Stu> stuList = stuMapper.list(stuNo, stuName, className, gender, school, stuScore);
        Page<Stu> p = (Page<Stu>) stuList;

        //3. 封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public Stu getById(Integer id) {
        return stuMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class) //Spring事务管理
    @Override
    public void deleteByIds(List<Integer> ids) {
        /*int i = 1 / 0;*/
        stuMapper.deleteByIds(ids);
    }

    @Override
    public void update(Stu stu) {
        stu.setUpdateTime(LocalDateTime.now());
        stuMapper.update(stu);
    }

    @Override
    public Stu login(Stu stu) {
        stu.setPassword(DigestUtils.md5Hex(stu.getPassword()));
        return stuMapper.getByStuNoAndPassword(stu);
    }
}

package com.hao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hao.mapper.DataMapper;
import com.hao.pojo.CfProblem;
import com.hao.pojo.PageBean;
import com.hao.pojo.SubNum;
import com.hao.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public List<String> getScore(String stuNo) {
        return dataMapper.getScore(stuNo);
    }

    @Override
    public List<SubNum> getSub() {
        return dataMapper.getSub();
    }

    @Override
    public PageBean getTrain(Integer page, Integer pageSize, String stuNo) {
        PageHelper.startPage(page, pageSize);
        List<CfProblem> cfProblemList = dataMapper.getProblemByScore(stuNo);
        Page<CfProblem> p = (Page<CfProblem>) cfProblemList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }
}

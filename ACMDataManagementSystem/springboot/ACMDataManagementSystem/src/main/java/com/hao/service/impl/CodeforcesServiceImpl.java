package com.hao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hao.mapper.CodeforcesMapper;
import com.hao.pojo.CfContest;
import com.hao.pojo.PageBean;
import com.hao.service.CodeforcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeforcesServiceImpl implements CodeforcesService {

    @Autowired
    private CodeforcesMapper codeforcesMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, Integer contestId,
                         String contestName, String beginTime, String length) {
        PageHelper.startPage(page, pageSize);
        List<CfContest> cfContestList = codeforcesMapper.list(contestId, contestName, beginTime, length);
        Page<CfContest> p = (Page<CfContest>) cfContestList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }
}

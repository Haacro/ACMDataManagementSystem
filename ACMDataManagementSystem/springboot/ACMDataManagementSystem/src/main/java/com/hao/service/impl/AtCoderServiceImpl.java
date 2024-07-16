package com.hao.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hao.mapper.AtCoderMapper;
import com.hao.pojo.AcContest;
import com.hao.pojo.AcProblem;
import com.hao.pojo.PageBean;
import com.hao.service.AtCoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtCoderServiceImpl implements AtCoderService {

    @Autowired
    private AtCoderMapper atCoderMapper;

    @Override
    public PageBean contestPage(Integer page, Integer pageSize, Integer contestId, String contestName, String beginTime, String length, String url) {
        PageHelper.startPage(page, pageSize);
        List<AcContest> acContestList = atCoderMapper.contestList(contestId, contestName, beginTime, length, url);
        Page<AcContest> p = (Page<AcContest>) acContestList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public PageBean problemPage(Integer page, Integer pageSize, String problemId, String problemName, String problemRate, String url) {
        PageHelper.startPage(page, pageSize);
        List<AcProblem> acContestList = atCoderMapper.problemList(problemId, problemName, problemRate, url);
        Page<AcProblem> p = (Page<AcProblem>) acContestList;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }
}

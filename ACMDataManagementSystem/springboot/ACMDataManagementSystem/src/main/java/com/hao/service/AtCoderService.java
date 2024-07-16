package com.hao.service;

import com.hao.pojo.PageBean;

public interface AtCoderService {
    PageBean contestPage(Integer page, Integer pageSize, Integer contestId, String contestName, String beginTime, String length, String url);

    PageBean problemPage(Integer page, Integer pageSize, String problemId, String problemName, String problemRate, String url);
}

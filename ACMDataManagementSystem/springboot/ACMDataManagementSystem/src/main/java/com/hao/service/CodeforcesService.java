package com.hao.service;

import com.hao.pojo.CfProblem;
import com.hao.pojo.PageBean;

public interface CodeforcesService {
    PageBean contestPage(Integer page, Integer pageSize, Integer contestId,
                  String contestName, String beginTime, String length);

    CfProblem getByContestId(Integer contestId);

    PageBean problemPage(Integer page, Integer pageSize, Integer contestId, String problemId,
                         String problemName, String problemRate, String tag);
}

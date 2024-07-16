package com.hao.service;

import com.hao.pojo.CfProblem;
import com.hao.pojo.PageBean;
import com.hao.pojo.Stu;
import com.hao.pojo.UpdateScore;

public interface CodeforcesService {
    PageBean contestPage(Integer page, Integer pageSize, Integer contestId,
                  String contestName, String beginTime, String length);

    CfProblem getByContestId(Integer contestId);

    PageBean problemPage(Integer page, Integer pageSize, Integer contestId, String problemId,
                         String problemName, String problemRate, String tag);


    void update(UpdateScore updateScore);
}

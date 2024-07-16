package com.hao.mapper;

import com.hao.pojo.AcContest;
import com.hao.pojo.AcProblem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AtCoderMapper {
    List<AcContest> contestList(Integer contestId, String contestName, String beginTime, String length, String url);

    List<AcProblem> problemList(String problemId, String problemName, String problemRate, String url);
}

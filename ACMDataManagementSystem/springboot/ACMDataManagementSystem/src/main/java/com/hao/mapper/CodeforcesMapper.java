package com.hao.mapper;

import com.hao.pojo.CfContest;
import com.hao.pojo.CfProblem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CodeforcesMapper {
    List<CfContest> contestList(Integer contestId, String contestName, String beginTime, String length);

    @Select("select id, contest_id, problem_id, problem_name, problem_rate, tag, url," +
            "create_time, update_time from cf_problem where contest_id = #{contestId}")
    CfProblem getByContestId(Integer contestId);

    List<CfProblem> problemList(Integer contestId, String problemId,
                                String problemName, String problemRate, String tag);
}

package com.hao.mapper;

import com.hao.pojo.CfContest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeforcesMapper {
    public List<CfContest> list(Integer contestId, String contestName, String beginTime, String length);
}

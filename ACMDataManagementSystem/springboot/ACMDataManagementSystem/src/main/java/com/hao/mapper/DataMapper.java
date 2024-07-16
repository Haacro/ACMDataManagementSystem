package com.hao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataMapper {
    @Select("select current_score from score_history " +
            "where stu_id = #{id} order by update_time")
    List<Integer> getScore(Integer id);
}

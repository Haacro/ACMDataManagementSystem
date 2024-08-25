package com.hao.mapper;

import com.hao.pojo.CfProblem;
import com.hao.pojo.SubNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataMapper {
    @Select("select current_score from score_history " +
            "where stu_id = (select id from stu where stu_no = #{stuNo}) " +
            "order by update_time")
    List<String> getScore(String stuNo);

    @Select("SELECT s.stu_Name, COUNT(sh.current_score) AS subNum FROM stu s " +
            "JOIN score_history sh ON s.id = sh.stu_id GROUP BY s.id, s.stu_Name")
    List<SubNum> getSub();

    List<CfProblem> getProblemByScore(String stuNo);
}

package com.hao.mapper;

import com.hao.pojo.Stu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuMapper {
    @Select("select id, stu_no, stu_name, class_name, gender," +
            "school, stu_score, create_time, update_time from stu")
    List<Stu> getAll();

    @Delete("delete from stu where id = #{id}")
    void deleteById(Integer id);
}

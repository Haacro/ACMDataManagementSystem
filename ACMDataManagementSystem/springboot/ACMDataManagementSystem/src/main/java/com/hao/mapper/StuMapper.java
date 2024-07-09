package com.hao.mapper;

import com.hao.pojo.Stu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StuMapper {
    /*@Select("select id, stu_no, stu_name, class_name, gender," +
            "school, stu_score, create_time, update_time from stu")
    List<Stu> getAll();*/

    /*@Delete("delete from stu where id = #{id}")
    void deleteById(Integer id);*/

    @Insert("insert into stu(stu_no, stu_name, class_name, gender, school, stu_score, create_time, update_time)" +
            "values(#{stuNo},#{stuName},#{className},#{gender},#{school},#{stuScore},#{createTime},#{updateTime})")
    void insert(Stu stu);

    /*PageHelper分页查询*/
    /*@Select("select id, stu_no, stu_name, class_name, gender," +
            "school, stu_score, create_time, update_time from stu")*/
    public List<Stu> list(String stuNo, String stuName, String className, Short gender, String school);

    @Select("select id, stu_no, stu_name, class_name, gender, school," +
            "stu_score, create_time, update_time from stu where id = #{id}")
    Stu getById(Integer id);

    void deleteByIds(List<Integer> ids);

    void update(Stu stu);

    @Select("select * from stu where stu_no = #{stuNo} and password = #{password}")
    Stu getByStuNoAndPassword(Stu stu);
}

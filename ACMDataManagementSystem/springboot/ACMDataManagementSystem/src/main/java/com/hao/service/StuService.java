package com.hao.service;

import com.hao.pojo.PageBean;
import com.hao.pojo.Stu;

import java.util.List;

public interface StuService {
    /*List<Stu> getAll();*/

    /*void deleteById(Integer id);*/

    void add(Stu stu);

    PageBean page(Integer page, Integer pageSize, String stuNo, String stuName,
                  String className, Short gender, String school);

    void deleteByIds(List<Integer> ids);
}

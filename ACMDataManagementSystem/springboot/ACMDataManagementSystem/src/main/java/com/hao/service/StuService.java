package com.hao.service;

import com.hao.pojo.Stu;

import java.util.List;

public interface StuService {
    List<Stu> getAll();

    void deleteById(Integer id);
}

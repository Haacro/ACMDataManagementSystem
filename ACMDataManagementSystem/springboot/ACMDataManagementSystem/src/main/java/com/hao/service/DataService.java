package com.hao.service;

import com.hao.pojo.SubNum;

import java.util.List;

public interface DataService {
    List<String> getScore(String stuNo);

    List<SubNum> getSub();
}

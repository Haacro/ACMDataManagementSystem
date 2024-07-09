package com.hao.service;

import com.hao.pojo.PageBean;

public interface CodeforcesService {
    PageBean page(Integer page, Integer pageSize, Integer contestId,
                  String contestName, String beginTime, String length);
}

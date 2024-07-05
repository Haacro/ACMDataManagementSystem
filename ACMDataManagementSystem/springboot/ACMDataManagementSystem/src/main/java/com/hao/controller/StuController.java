package com.hao.controller;

import com.hao.pojo.Result;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping("/stus")
    public Result getAll() {
        log.info("查询全部学生用户信息");
        List<Stu> stuList = stuService.getAll();
        return Result.success(stuList);
    }
}

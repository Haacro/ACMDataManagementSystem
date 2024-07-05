package com.hao.controller;

import com.hao.pojo.Test;
import com.hao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//请求处理类
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/")
    public List<Test> testListTest() {
        List<Test> testList = testService.listTest();
        System.out.println(testList);
        return testList;
    }
}

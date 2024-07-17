package com.hao.controller;

import com.hao.pojo.Result;
import com.hao.pojo.SubNum;
import com.hao.service.DataService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:7000"}) //允许跨域请求
@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/score/{stuNo}")
    public Result getScore(@PathVariable String stuNo) {
        log.info("查询学生：{}历史积分", stuNo);
        List<String> stringList = dataService.getScore(stuNo);
        return Result.success(stringList);
    }

    @GetMapping("/sub")
    public Result getSub() {
        log.info("查询学生提交次数");
        List<SubNum> subNumList = dataService.getSub();
        return Result.success(subNumList);
    }
}

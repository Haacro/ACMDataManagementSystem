package com.hao.controller;

import com.hao.pojo.Result;
import com.hao.service.DataService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/score/{id}")
    public Result getScore(@PathVariable Integer id) {
        log.info("查询学生：{}历史积分", id);
        List<Integer> integerList = dataService.getScore(id);
        return Result.success(integerList);
    }
}

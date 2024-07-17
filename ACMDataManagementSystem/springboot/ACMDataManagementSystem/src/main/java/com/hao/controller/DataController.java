package com.hao.controller;

import com.hao.pojo.Result;
import com.hao.pojo.SubNum;
import com.hao.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "data接口")
@Slf4j
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:7000"}) //允许跨域请求
@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @Operation(summary = "查询学生历史积分", description = "根据学生编号查询该学生的历史积分记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回学生历史积分记录"),
            @ApiResponse(responseCode = "404", description = "未找到学生历史积分记录")
    })
    @GetMapping("/score/{stuNo}")
    public Result getScore(@PathVariable @Parameter(description = "学生编号") String stuNo) {
        log.info("查询学生：{}历史积分", stuNo);
        List<String> stringList = dataService.getScore(stuNo);
        return Result.success(stringList);
    }

    @Operation(summary = "查询学生提交次数", description = "查询所有学生的提交次数记录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回学生提交次数记录"),
            @ApiResponse(responseCode = "404", description = "未找到学生提交次数记录")
    })
    @GetMapping("/sub")
    public Result getSub() {
        log.info("查询学生提交次数");
        List<SubNum> subNumList = dataService.getSub();
        return Result.success(subNumList);
    }
}

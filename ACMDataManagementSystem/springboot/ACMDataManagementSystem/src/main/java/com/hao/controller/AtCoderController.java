package com.hao.controller;

import com.hao.pojo.PageBean;
import com.hao.pojo.Result;
import com.hao.service.AtCoderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AtCoder接口")
@Slf4j
@RequestMapping("/atcoder")
@RestController
public class AtCoderController {

    @Autowired
    private AtCoderService atCoderService;

    @Operation(summary = "分页查询AC比赛", description = "根据参数分页查询AtCoder比赛信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回比赛分页信息"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @GetMapping("/contests")
    public Result contestPage(
            @RequestParam(defaultValue = "1") @Parameter(description = "页码，默认值为1") Integer page,
            @RequestParam(defaultValue = "10") @Parameter(description = "每页显示条数，默认值为10") Integer pageSize,
            @Parameter(description = "比赛ID") Integer contestId,
            @Parameter(description = "比赛名称") String contestName,
            @Parameter(description = "比赛开始时间") String beginTime,
            @Parameter(description = "比赛时长") String length,
            @Parameter(description = "比赛URL") String url) {
        log.info("分页查询ac比赛，参数:{},{},{},{},{},{}", page, pageSize, contestId, contestName, beginTime, length, url);
        PageBean pageBean = atCoderService.contestPage(page, pageSize, contestId, contestName, beginTime, length, url);
        return Result.success(pageBean);
    }

    @Operation(summary = "分页查询AC题目", description = "根据条件分页查询AtCoder题目信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回题目分页信息"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @GetMapping("/problems")
    public Result problemPage(
            @RequestParam(defaultValue = "1") @Parameter(description = "页码，默认值为1") Integer page,
            @RequestParam(defaultValue = "10") @Parameter(description = "每页显示条数，默认值为10") Integer pageSize,
            @Parameter(description = "题目ID") String problemId,
            @Parameter(description = "题目名称") String problemName,
            @Parameter(description = "题目评分") String problemRate,
            @Parameter(description = "题目URL") String url) {
        log.info("分页查询cf题目，参数:{},{},{},{}", problemId, problemName, problemRate, url);
        PageBean pageBean = atCoderService.problemPage(page, pageSize, problemId, problemName, problemRate, url);
        return Result.success(pageBean);
    }
}

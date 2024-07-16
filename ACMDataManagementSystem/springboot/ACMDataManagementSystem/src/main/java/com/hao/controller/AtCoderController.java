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

    @Operation(summary = "Get acContests page", description = "条件分页查询ac比赛列表")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved contests page"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/contests")
    public Result contestPage(
            @Parameter(description = "Page number", example = "1", required = false)
            @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "Page size", example = "10", required = false)
            @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(description = "Contest ID", example = "679", required = false)
            @RequestParam(required = false) Integer contestId,
            @Parameter(description = "Contest name", example = "Tokio Marine & Nichido Fire Insurance Programming Contest 2020", required = false)
            @RequestParam(required = false) String contestName,
            @Parameter(description = "Contest start time", example = "2020-06-13 21:00:00+09005", required = false)
            @RequestParam(required = false) String beginTime,
            @Parameter(description = "Contest length", example = "02:00", required = false)
            @RequestParam(required = false) String length,
            @Parameter(description = "Contest url", example = "https://atcoder.jp/contests/tokiomarine2020", required = false)
            @RequestParam(required = false) String url) {
        log.info("分页查询ac比赛，参数:{},{},{},{},{},{}", page, pageSize, contestId, contestName, beginTime, length, url);
        PageBean pageBean = atCoderService.contestPage(page, pageSize, contestId, contestName, beginTime, length, url);
        return Result.success(pageBean);
    }

    @Operation(summary = "Get problems page", description = "条件分页查询cf题目列表")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved problems page"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/problems")
    public Result problemPage(
            @Parameter(description = "Page number", example = "1", required = false)
            @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "Page size", example = "10", required = false)
            @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(description = "Problem ID", example = "A", required = false)
            @RequestParam(required = false) String problemId,
            @Parameter(description = "Problem name", example = "AtCoder Line", required = false)
            @RequestParam(required = false) String problemName,
            @Parameter(description = "Problem rating", example = "100", required = false)
            @RequestParam(required = false) String problemRate,
            @Parameter(description = "Problem url", example = "https://atcoder.jp/contests/abc352/tasks/abc352_a", required = false)
            @RequestParam(required = false) String url) {
        log.info("分页查询cf题目，参数:{},{},{},{}",  problemId, problemName, problemRate, url);
        PageBean pageBean = atCoderService.problemPage(page, pageSize, problemId, problemName, problemRate, url);
        return Result.success(pageBean);
    }
}

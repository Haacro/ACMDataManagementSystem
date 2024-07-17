package com.hao.controller;

import com.hao.pojo.CfProblem;
import com.hao.pojo.PageBean;
import com.hao.pojo.Result;
import com.hao.pojo.UpdateScore;
import com.hao.service.CodeforcesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Codeforces接口")
@Slf4j
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:7000"}) //允许跨域请求
@RequestMapping("/codeforces")
@RestController
public class CodeforcesController {

    @Autowired
    private CodeforcesService codeforcesService;

    @Operation(summary = "分页查询cf比赛", description = "根据条件分页查询codeforces比赛")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回比赛分页数据"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @GetMapping("/contests")
    public Result contestPage(
            @RequestParam(defaultValue = "1") @Parameter(description = "页码") Integer page,
            @RequestParam(defaultValue = "10") @Parameter(description = "每页显示条数") Integer pageSize,
            @RequestParam(required = false) @Parameter(description = "比赛ID") Integer contestId,
            @RequestParam(required = false) @Parameter(description = "比赛名称") String contestName,
            @RequestParam(required = false) @Parameter(description = "比赛开始时间") String beginTime,
            @RequestParam(required = false) @Parameter(description = "比赛时长") String length) {
        log.info("分页查询cf比赛，参数:{},{},{},{},{},{}", page, pageSize, contestId, contestName, beginTime, length);
        PageBean pageBean = codeforcesService.contestPage(page, pageSize, contestId, contestName, beginTime, length);
        return Result.success(pageBean);
    }

    @Operation(summary = "根据contestId查询题目", description = "根据contestId查询特定比赛的题目")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回比赛题目数据"),
            @ApiResponse(responseCode = "404", description = "未找到相关比赛题目")
    })
    @GetMapping("/{contestId}")
    public Result getByContestId(@PathVariable @Parameter(description = "比赛ID") Integer contestId) {
        log.info("根据contestId查询题目,contestId:{}", contestId);
        CfProblem cfProblem = codeforcesService.getByContestId(contestId);
        return Result.success(cfProblem);
    }

    @Operation(summary = "条件分页查询cf题目", description = "根据条件分页查询codeforces题目")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回题目分页数据"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @GetMapping("/problems")
    public Result problemPage(
            @RequestParam(defaultValue = "1") @Parameter(description = "页码，默认值为1") Integer page,
            @RequestParam(defaultValue = "10") @Parameter(description = "每页显示条数，默认值为10") Integer pageSize,
            @Parameter(description = "比赛ID") Integer contestId,
            @Parameter(description = "题目ID") String problemId,
            @Parameter(description = "题目名称") String problemName,
            @Parameter(description = "题目评分") String problemRate,
            @Parameter(description = "题目标签") String tag) {
        log.info("条件分页查询cf题目，参数:{},{},{},{},{}", contestId, problemId, problemName, problemRate, tag);
        PageBean pageBean = codeforcesService.problemPage(page, pageSize, contestId, problemId, problemName, problemRate, tag);
        return Result.success(pageBean);
    }

    @Operation(summary = "更新用户积分", description = "更新用户在Codeforces比赛中的积分")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @PostMapping("/problems")
    public Result update(@RequestBody @Parameter(description = "更新积分信息") UpdateScore updateScore) {
        log.info("更新用户积分:{}", updateScore);
        codeforcesService.update(updateScore);
        return Result.success();
    }
}

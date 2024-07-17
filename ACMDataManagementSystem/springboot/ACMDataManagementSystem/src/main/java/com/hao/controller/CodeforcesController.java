package com.hao.controller;

import com.hao.pojo.CfProblem;
import com.hao.pojo.PageBean;
import com.hao.pojo.Result;
import com.hao.pojo.UpdateScore;
import com.hao.service.CodeforcesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(summary = "Get cfContests page", description = "条件分页查询cf比赛列表")
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
            @Parameter(description = "Contest ID", example = "1983", required = false)
            @RequestParam(required = false) Integer contestId,
            @Parameter(description = "Contest name", example = "Codeforces Round (Div. 2)", required = false)
            @RequestParam(required = false) String contestName,
            @Parameter(description = "Contest start time", example = "Jun/30/2024 17:35", required = false)
            @RequestParam(required = false) String beginTime,
            @Parameter(description = "Contest length", example = "02:15", required = false)
            @RequestParam(required = false) String length) {
        log.info("分页查询cf比赛，参数:{},{},{},{},{},{}", page, pageSize, contestId, contestName, beginTime, length);
        PageBean pageBean = codeforcesService.contestPage(page, pageSize, contestId, contestName, beginTime, length);
        return Result.success(pageBean);
    }

    @Operation(summary = "Get contest by ID", description = "根据contestId查询题目")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved contest"),
            @ApiResponse(responseCode = "404", description = "Contest not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{contestId}")
    public Result getByContestId(
            @Parameter(description = "Contest ID", in = ParameterIn.PATH, required = true, schema = @Schema(type = "integer", example = "1983"))
            @PathVariable Integer contestId) {
        log.info("根据contestId查询题目,contestId:{}", contestId);
        CfProblem cfProblem = codeforcesService.getByContestId(contestId);
        return Result.success(cfProblem);
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
            @Parameter(description = "Contest ID", example = "1983", required = false)
            @RequestParam(required = false) Integer contestId,
            @Parameter(description = "Problem ID", example = "A", required = false)
            @RequestParam(required = false) String problemId,
            @Parameter(description = "Problem name", example = "Two Sum", required = false)
            @RequestParam(required = false) String problemName,
            @Parameter(description = "Problem rating", example = "1200", required = false)
            @RequestParam(required = false) String problemRate,
            @Parameter(description = "Problem tag", example = "greedy", required = false)
            @RequestParam(required = false) String tag) {
        log.info("条件分页查询cf题目，参数:{},{},{},{},{}", contestId, problemId, problemName, problemRate, tag);
        PageBean pageBean = codeforcesService.problemPage(page, pageSize, contestId, problemId, problemName, problemRate, tag);
        return Result.success(pageBean);
    }

    @Operation(summary = "Update the score", description = "更新积分")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated student", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/problems")
    public Result update(
            @Parameter(description = "Student object to be updated", required = true)
            @RequestBody UpdateScore updateScore) {
        log.info("更新用户积分:{}", updateScore);
        codeforcesService.update(updateScore);
        return Result.success();
    }
}

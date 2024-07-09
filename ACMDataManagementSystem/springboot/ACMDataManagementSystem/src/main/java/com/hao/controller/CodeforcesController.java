package com.hao.controller;

import com.hao.pojo.PageBean;
import com.hao.pojo.Result;
import com.hao.service.CodeforcesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "codeforces接口")
@Slf4j
@RequestMapping("/codeforces")
@RestController
public class CodeforcesController {

    @Autowired
    private CodeforcesService codeforcesService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Integer contestId, String contestName, String beginTime, String length) {
        log.info("分页查询cf比赛，参数:{},{},{},{},{},{}", page, pageSize, contestId, contestName, beginTime, length);
        PageBean pageBean = codeforcesService.page(page, pageSize, contestId, contestName, beginTime, length);
        return Result.success(pageBean);
    }
}

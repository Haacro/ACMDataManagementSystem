package com.hao.controller;

import com.hao.pojo.Result;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/stus")
@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping
    public Result getAll() {
        log.info("查询全部学生用户信息");
        List<Stu> stuList = stuService.getAll();
        return Result.success(stuList);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据ID删除学生用户:{}", id);
        stuService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Stu stu) {
        log.info("新增学生用户:{}",stu.getStuName());
        stuService.add(stu);
        return Result.success();
    }
}

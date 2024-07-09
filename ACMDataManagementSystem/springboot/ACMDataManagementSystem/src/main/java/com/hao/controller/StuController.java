package com.hao.controller;

import com.hao.pojo.PageBean;
import com.hao.pojo.Result;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/stus")
@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    /*@GetMapping
    public Result getAll() {
        log.info("查询全部学生用户信息");
        List<Stu> stuList = stuService.getAll();
        return Result.success(stuList);
    }*/

    /*@DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据ID删除学生用户:{}", id);
        stuService.deleteById(id);
        return Result.success();
    }*/

    @PostMapping
    public Result add(@RequestBody Stu stu) {
        log.info("新增学生用户:{}", stu.getStuName());
        stuService.add(stu);
        return Result.success();
    }

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String stuNo, String stuName, String className, Short gender, String school
            /*, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate time*/) {
        log.info("分页查询，参数:{},{},{},{},{},{},{}", page, pageSize, stuNo, stuName, className, gender, school);
        PageBean pageBean = stuService.page(page, pageSize, stuNo, stuName, className, gender, school);
        return Result.success(pageBean);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学生用户,id:{}", id);
        Stu stu = stuService.getById(id);
        return Result.success(stu);
    }

    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("批量删除，ids:{}", ids);
        stuService.deleteByIds(ids);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Stu stu) {
        log.info("更新学生用户信息:{}",stu);
        stuService.update(stu);
        return Result.success();
    }
}

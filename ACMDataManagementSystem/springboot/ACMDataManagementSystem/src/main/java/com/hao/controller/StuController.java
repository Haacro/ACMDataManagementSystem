package com.hao.controller;

import com.hao.anno.Log;
import com.hao.pojo.PageBean;
import com.hao.pojo.Result;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Lazy //延迟初始化 第一次使用时再实例化
//@Scope("prototype") //设置该Bean作用域为非单例
@Tag(name = "stus接口")
@Slf4j
@CrossOrigin(origins = "http://localhost:8081") //允许跨域请求
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

    @PostMapping("/register")
    public Result register(@RequestBody @Validated Stu stu) {
        Stu s = stuService.findByStuNo(stu.getStuNo());
        if (s == null) {
            stuService.register(stu);
            return Result.success();
        } else {
            return Result.error("该学号已注册");
        }
    }

    @Log
    @Operation(summary = "新增学生用户", description = "添加一个新的学生用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "新增成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @PostMapping
    public Result add(@RequestBody @Parameter(description = "学生用户信息") Stu stu) {
        log.info("新增学生用户:{}", stu.getStuName());
        stuService.add(stu);
        return Result.success();
    }

    @Operation(summary = "分页查询学生用户", description = "根据参数分页查询学生用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回学生用户分页信息"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @GetMapping
    public Result page(
            @RequestParam(defaultValue = "1") @Parameter(description = "页码，默认值为1") Integer page,
            @RequestParam(defaultValue = "10") @Parameter(description = "每页显示条数，默认值为10") Integer pageSize,
            @Parameter(description = "学生编号") String stuNo,
            @RequestParam(required = false) @Parameter(description = "学生姓名") String stuName,
            @Parameter(description = "班级名称") String className,
            @Parameter(description = "性别") Short gender,
            @Parameter(description = "学校") String school,
            @Parameter(description = "学生成绩") Integer stuScore
            /*, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate time*/) {
        log.info("分页查询学生用户，参数:{},{},{},{},{},{},{},{}", page, pageSize, stuNo, stuName, className, gender, school, stuScore);
        PageBean pageBean = stuService.page(page, pageSize, stuNo, stuName, className, gender, school, stuScore);
        return Result.success(pageBean);
    }

    @Operation(summary = "根据ID查询学生用户", description = "根据学生用户ID查询详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功，返回学生用户信息"),
            @ApiResponse(responseCode = "404", description = "未找到学生用户")
    })
    @GetMapping("/{id}")
    public Result getById(@PathVariable @Parameter(description = "学生用户ID") Integer id) {
        log.info("根据ID查询学生用户,id:{}", id);
        Stu stu = stuService.getById(id);
        return Result.success(stu);
    }

    @Log
    @Operation(summary = "批量删除学生用户", description = "根据学生用户ID列表批量删除学生用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable @Parameter(description = "学生用户ID列表") List<Integer> ids) {
        log.info("批量删除，ids:{}", ids);
        stuService.deleteByIds(ids);
        return Result.success();
    }

    @Log
    @Operation(summary = "更新学生用户信息", description = "更新学生用户的详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    @PutMapping
    public Result update(@RequestBody @Parameter(description = "学生用户信息") Stu stu) {
        log.info("更新学生用户信息:{}", stu);
        stuService.update(stu);
        return Result.success();
    }
}

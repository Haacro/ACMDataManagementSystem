package com.hao.controller;

import com.hao.pojo.PageBean;
import com.hao.pojo.Result;
import com.hao.pojo.Stu;
import com.hao.service.StuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "stus接口")
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

    @Operation(summary = "Add a new student", description = "新增学生用户")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully added student",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public Result add(
            @Parameter(description = "Student object to be added", required = true)
            @RequestBody Stu stu) {
        log.info("新增学生用户:{}", stu.getStuName());
        stuService.add(stu);
        return Result.success();
    }

    @Operation(summary = "Get stu page", description = "条件分页查询学生用户")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated list", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public Result page(
            @Parameter(description = "Page number", example = "1", required = false)
            @RequestParam(defaultValue = "1") Integer page,
            @Parameter(description = "Page size", example = "10", required = false)
            @RequestParam(defaultValue = "10") Integer pageSize,
            @Parameter(description = "Student number", example = "2021040250", required = true)
            @RequestParam(required = false) String stuNo,
            @Parameter(description = "Student name", example = "郝佳宁", required = true)
            @RequestParam(required = false) String stuName,
            @Parameter(description = "Class name", example = "计科2102", required = true)
            @RequestParam(required = false) String className,
            @Parameter(description = "Gender", example = "1", schema = @Schema(type = "integer", allowableValues = {"0", "1"}), required = true)
            @RequestParam(required = false) Short gender,
            @Parameter(description = "School name", example = "北京化工大学", required = true)
            @RequestParam(required = false) String school
            /*, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate time*/) {
        log.info("分页查询学生用户，参数:{},{},{},{},{},{},{}", page, pageSize, stuNo, stuName, className, gender, school);
        PageBean pageBean = stuService.page(page, pageSize, stuNo, stuName, className, gender, school);
        return Result.success(pageBean);
    }

    @Operation(summary = "Get student by ID", description = "根据ID查询学生用户 ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved student", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public Result getById(
            @Parameter(description = "Student ID", in = ParameterIn.PATH, required = true, schema = @Schema(type = "integer", example = "1"))
            @PathVariable Integer id) {
        log.info("根据ID查询学生用户,id:{}", id);
        Stu stu = stuService.getById(id);
        return Result.success(stu);
    }

    @Operation(summary = "Delete students by IDs", description = "批量删除")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully deleted students", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Students not found")
    })
    @DeleteMapping("/{ids}")
    public Result deleteByIds(
            @Parameter(description = "List of student IDs to delete", in = ParameterIn.PATH, required = true)
            @PathVariable List<Integer> ids) {
        log.info("批量删除，ids:{}", ids);
        stuService.deleteByIds(ids);
        return Result.success();
    }

    @Operation(summary = "Update a student", description = "更新学生用户信息")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated student", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping
    public Result update(
            @Parameter(description = "Student object to be updated", required = true)
            @RequestBody Stu stu) {
        log.info("更新学生用户信息:{}", stu);
        stuService.update(stu);
        return Result.success();
    }
}

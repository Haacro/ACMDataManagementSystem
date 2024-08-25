package com.hao.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "学生实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {
    @Schema(description = "主键ID")
    private Integer id;

    @Schema(description = "学生编号")
    @NotEmpty(message = "学生编号不能为空")
    @Pattern(regexp = "^\\S{10}$", message = "学生编号必须是10位非空字符")
    private String stuNo;

    @Schema(description = "学生密码")
    private String password;

    @Schema(description = "学生姓名")
    private String stuName;

    @Schema(description = "班级名称")
    private String className;

    @Schema(description = "性别")
    private Short gender;

    @Schema(description = "学校")
    private String school;

    @Schema(description = "学生成绩")
    private Integer stuScore;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
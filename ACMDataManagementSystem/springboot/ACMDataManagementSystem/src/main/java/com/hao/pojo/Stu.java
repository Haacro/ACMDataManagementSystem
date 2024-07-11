package com.hao.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "学生用户信息实体类Stu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {
    @Schema(description = "主键ID", example = "1")
    private Integer id;
    @Schema(description = "学号", example = "2021040250")
    private String stuNo;
    @Schema(description = "密码", example = "123456")
    private String password;
    @Schema(description = "姓名", example = "郝佳宁")
    private String stuName;
    @Schema(description = "班级", example = "计科2102")
    private String className;
    @Schema(description = "性别（1：男性，2：女性）", example = "1", allowableValues = {"0", "1"})
    private Short gender;
    @Schema(description = "学校", example = "北京化工大学")
    private String school;
    @Schema(description = "积分", example = "500")
    private Integer stuScore;
    @Schema(description = "创建时间", example = "2024-07-10 09:12:01")
    private LocalDateTime createTime;
    @Schema(description = "修改时间", example = "2024-07-10 09:12:01")
    private LocalDateTime updateTime;
}
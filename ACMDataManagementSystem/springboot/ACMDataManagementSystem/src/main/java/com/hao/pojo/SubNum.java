package com.hao.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "学生提交次数实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubNum {
    @Schema(description = "学生姓名")
    private String stuName;

    @Schema(description = "提交次数")
    private Integer subNum;
}

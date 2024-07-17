package com.hao.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "更新学生成绩实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateScore {
    @Schema(description = "学生ID")
    private Integer stuId;

    @Schema(description = "题目评分")
    private String problemRate;
}

package com.hao.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "AtCoder题目实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcProblem {
    @Schema(description = "主键ID")
    private Integer id;

    @Schema(description = "题目ID")
    private String problemId;

    @Schema(description = "题目名称")
    private String problemName;

    @Schema(description = "题目评分")
    private String problemRate;

    @Schema(description = "题目URL")
    private String url;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}

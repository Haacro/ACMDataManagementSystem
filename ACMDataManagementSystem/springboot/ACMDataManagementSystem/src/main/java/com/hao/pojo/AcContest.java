package com.hao.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "AtCoder比赛实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcContest {
    @Schema(description = "主键ID")
    private Integer id;

    @Schema(description = "比赛ID")
    private Integer contestId;

    @Schema(description = "比赛名称")
    private String contestName;

    @Schema(description = "比赛开始时间")
    private String beginTime;

    @Schema(description = "比赛时长")
    private String length;

    @Schema(description = "比赛URL")
    private String url;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}

package com.hao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CfProblem {
    private Integer id;
    private Integer contestId;
    private String problemId;
    private String problemName;
    private String problemRate;
    private String tag;
    private String url;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

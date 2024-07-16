package com.hao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcProblem {
    private Integer id;
    private String problemId;
    private String problemName;
    private String problemRate;
    private String url;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

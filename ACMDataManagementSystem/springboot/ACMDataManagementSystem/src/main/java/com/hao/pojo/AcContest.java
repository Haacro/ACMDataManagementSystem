package com.hao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcContest {
    private Integer id;
    private Integer contestId;
    private String contestName;
    private String beginTime;
    private String length;
    private String url;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

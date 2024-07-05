package com.hao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stu {
    private Integer id;
    private String stuNo;
    private String stuName;
    private String className;
    private Integer gender;
    private String school;
    private Integer stuScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
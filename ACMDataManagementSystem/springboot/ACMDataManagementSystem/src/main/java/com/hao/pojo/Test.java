package com.hao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    private Integer id; //ID
    private String message; //测试信息
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}

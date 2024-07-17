package com.hao.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "分页Bean")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
    @Schema(description = "总记录数")
    private Long total;

    @Schema(description = "分页数据列表")
    private List rows;
}

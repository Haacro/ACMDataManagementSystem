package com.hao.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "通用结果返回类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Schema(description = "响应状态码，1表示成功，0表示失败")
    private Integer code;

    @Schema(description = "响应消息")
    private String msg;

    @Schema(description = "响应数据")
    private Object data;

    /**
     * 创建一个成功响应，无数据返回。
     *
     * @return 成功响应对象
     */
    public static Result success() {
        return new Result(1, "success", null);
    }

    /**
     * 创建一个成功响应，包含数据返回。
     *
     * @param data 响应数据
     * @return 成功响应对象
     */
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    /**
     * 创建一个失败响应。
     *
     * @param msg 失败信息
     * @return 失败响应对象
     */
    public static Result error(String msg) {
        return new Result(0, msg, null);
    }
}

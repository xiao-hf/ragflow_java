package com.xiao.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 通用响应对象
 *
 * @param <T> 响应数据类型
 */
@Data
@Schema(description = "通用响应结果")
public class Result<T> {

    @Schema(description = "响应码，200表示成功", example = "200")
    private Integer code;

    @Schema(description = "响应消息", example = "操作成功")
    private String message;

    @Schema(description = "响应数据")
    private T data;

    /**
     * 成功响应
     *
     * @param <T> 数据类型
     * @return 成功响应结果
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        return result;
    }

    /**
     * 成功响应，带数据
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功响应结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 成功响应，带消息和数据
     *
     * @param message 响应消息
     * @param data    响应数据
     * @param <T>     数据类型
     * @return 成功响应结果
     */
    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 失败响应
     *
     * @param <T> 数据类型
     * @return 失败响应结果
     */
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setCode(1000);
        result.setMessage("操作失败");
        return result;
    }

    /**
     * 失败响应，带消息
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return 失败响应结果
     */
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(1000);
        result.setMessage(message);
        return result;
    }

    /**
     * 失败响应，带错误码和消息
     *
     * @param code    错误码
     * @param message 错误消息
     * @param <T>     数据类型
     * @return 失败响应结果
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
} 
package com.xiao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录请求DTO
 */
@Data
@Schema(description = "登录请求")
public class LoginRequest {

    @Schema(description = "用户名", required = true, example = "admin")
    private String username;

    @Schema(description = "密码", required = true, example = "password123")
    private String password;
}
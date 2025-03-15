package com.xiao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 注册请求DTO
 */
@Data
@Schema(description = "注册请求")
public class RegisterRequest {

    @Schema(description = "用户名", required = true, example = "johndoe")
    private String username;

    @Schema(description = "密码", required = true, example = "password123")
    private String password;

    @Schema(description = "昵称", example = "John Doe")
    private String nickname;

    @Schema(description = "电子邮箱", example = "john@example.com")
    private String email;
    
    @Schema(description = "IP地址", example = "192.168.1.1")
    private String ipAddress;
} 
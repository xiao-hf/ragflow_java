package com.xiao.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户实体类
 */
@Data
@Schema(description = "用户实体")
public class User {

    @Schema(description = "用户ID", example = "1")
    private Long id;

    @Schema(description = "用户名", required = true, example = "admin")
    private String username;

    @Schema(description = "密码", required = true, example = "******", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    @Schema(description = "电子邮箱", example = "user@example.com")
    private String email;

    @Schema(description = "用户状态: 0-禁用, 1-正常", example = "1")
    private Integer status;
} 
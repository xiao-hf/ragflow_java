package com.xiao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 使用旧版Swagger注解的用户实体类
 */
@Data
@ApiModel(description = "用户实体")
public class UserSwagger {

    @ApiModelProperty(value = "用户ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "******", hidden = true)
    private String password;

    @ApiModelProperty(value = "电子邮箱", example = "user@example.com")
    private String email;

    @ApiModelProperty(value = "用户状态: 0-禁用, 1-正常", example = "1")
    private Integer status;
} 
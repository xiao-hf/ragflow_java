package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 */
@ApiModel(description = "用户表")
@Data
public class User {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码(加密存储)
     */
    @ApiModelProperty(value = "密码(加密存储)")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 头像URL
     */
    @ApiModelProperty(value = "头像URL")
    private String avatar;

    /**
     * 角色(admin, user)
     */
    @ApiModelProperty(value = "角色(admin, user)")
    private String role;

    /**
     * 状态: 0-禁用,1-正常
     */
    @ApiModelProperty(value = "状态: 0-禁用,1-正常")
    private Integer status;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime;

    /**
     * 最后登录IP
     */
    @ApiModelProperty(value = "最后登录IP")
    private String lastLoginIp;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
}
package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 系统日志表
 */
@ApiModel(description = "系统日志表")
@Data
public class Logs {
    /**
     * 日志ID
     */
    @ApiModelProperty(value = "日志ID")
    private Long id;

    /**
     * 用户ID(可为空)
     */
    @ApiModelProperty(value = "用户ID(可为空)")
    private Long userId;

    /**
     * 动作类型
     */
    @ApiModelProperty(value = "动作类型")
    private String action;

    /**
     * 实体类型
     */
    @ApiModelProperty(value = "实体类型")
    private String entityType;

    /**
     * 实体ID
     */
    @ApiModelProperty(value = "实体ID")
    private String entityId;

    /**
     * 详细信息(JSON格式)
     */
    @ApiModelProperty(value = "详细信息(JSON格式)")
    private Object details;

    /**
     * IP地址
     */
    @ApiModelProperty(value = "IP地址")
    private String ipAddress;

    /**
     * 用户代理
     */
    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    /**
     * 成功状态: 0-失败,1-成功
     */
    @ApiModelProperty(value = "成功状态: 0-失败,1-成功")
    private Integer success;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    private String errorMessage;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
}
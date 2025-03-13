package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * API密钥表
 */
@ApiModel(description = "API密钥表")
@Data
public class ApiKeys {
    /**
     * API密钥ID
     */
    @ApiModelProperty(value = "API密钥ID")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 密钥名称
     */
    @ApiModelProperty(value = "密钥名称")
    private String keyName;

    /**
     * API密钥(加密存储)
     */
    @ApiModelProperty(value = "API密钥(加密存储)")
    private String apiKey;

    /**
     * 状态: 0-禁用,1-正常
     */
    @ApiModelProperty(value = "状态: 0-禁用,1-正常")
    private Integer status;

    /**
     * 最后使用时间
     */
    @ApiModelProperty(value = "最后使用时间")
    private Date lastUsedAt;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    private Date expiredAt;

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
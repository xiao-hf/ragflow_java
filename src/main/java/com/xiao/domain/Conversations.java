package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 会话表
 */
@ApiModel(description = "会话表")
@Data
public class Conversations {
    /**
     * 会话ID
     */
    @ApiModelProperty(value = "会话ID")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 知识库ID，可为空表示非知识库会话
     */
    @ApiModelProperty(value = "知识库ID，可为空表示非知识库会话")
    private Long knowledgeBaseId;

    /**
     * 会话标题
     */
    @ApiModelProperty(value = "会话标题")
    private String title;

    /**
     * 系统提示词
     */
    @ApiModelProperty(value = "系统提示词")
    private String systemPrompt;

    /**
     * 使用的模型
     */
    @ApiModelProperty(value = "使用的模型")
    private String model;

    /**
     * 温度参数
     */
    @ApiModelProperty(value = "温度参数")
    private BigDecimal temperature;

    /**
     * 状态: 0-已归档,1-正常
     */
    @ApiModelProperty(value = "状态: 0-已归档,1-正常")
    private Integer status;

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
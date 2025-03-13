package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 知识库表
 */
@ApiModel(description = "知识库表")
@Data
public class KnowledgeBases {
    /**
     * 知识库ID
     */
    @ApiModelProperty(value = "知识库ID")
    private Long id;

    /**
     * 知识库名称
     */
    @ApiModelProperty(value = "知识库名称")
    private String name;

    /**
     * 知识库描述
     */
    @ApiModelProperty(value = "知识库描述")
    private String description;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private Long creatorId;

    /**
     * 状态: 0-禁用,1-正常
     */
    @ApiModelProperty(value = "状态: 0-禁用,1-正常")
    private Integer status;

    /**
     * 嵌入模型
     */
    @ApiModelProperty(value = "嵌入模型")
    private String embeddingModel;

    /**
     * LLM模型
     */
    @ApiModelProperty(value = "LLM模型")
    private String llmModel;

    /**
     * 知识库配置(JSON格式)
     */
    @ApiModelProperty(value = "知识库配置(JSON格式)")
    private Object config;

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
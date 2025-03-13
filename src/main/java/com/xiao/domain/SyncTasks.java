package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 知识库同步任务表
 */
@ApiModel(description = "知识库同步任务表")
@Data
public class SyncTasks {
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private Long id;

    /**
     * 知识库ID
     */
    @ApiModelProperty(value = "知识库ID")
    private Long knowledgeBaseId;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private Long creatorId;

    /**
     * 任务类型: incremental, full
     */
    @ApiModelProperty(value = "任务类型: incremental, full")
    private String taskType;

    /**
     * 状态: 0-待执行,1-执行中,2-已完成,3-失败
     */
    @ApiModelProperty(value = "状态: 0-待执行,1-执行中,2-已完成,3-失败")
    private Integer status;

    /**
     * 进度百分比
     */
    @ApiModelProperty(value = "进度百分比")
    private BigDecimal progress;

    /**
     * 总文档数
     */
    @ApiModelProperty(value = "总文档数")
    private Integer totalDocuments;

    /**
     * 已处理文档数
     */
    @ApiModelProperty(value = "已处理文档数")
    private Integer processedDocuments;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    private String errorMessage;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startedAt;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private Date completedAt;

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
package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 用量统计表
 */
@ApiModel(description = "用量统计表")
@Data
public class UsageStatistics {
    /**
     * 统计ID
     */
    @ApiModelProperty(value = "统计ID")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Object date;

    /**
     * 会话数量
     */
    @ApiModelProperty(value = "会话数量")
    private Integer conversationCount;

    /**
     * 消息数量
     */
    @ApiModelProperty(value = "消息数量")
    private Integer messageCount;

    /**
     * 消耗token数量
     */
    @ApiModelProperty(value = "消耗token数量")
    private Integer tokenCount;

    /**
     * 提示token数量
     */
    @ApiModelProperty(value = "提示token数量")
    private Integer promptTokens;

    /**
     * 补全token数量
     */
    @ApiModelProperty(value = "补全token数量")
    private Integer completionTokens;

    /**
     * 嵌入请求数量
     */
    @ApiModelProperty(value = "嵌入请求数量")
    private Integer embeddingCount;

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
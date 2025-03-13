package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 检索结果表
 */
@ApiModel(description = "检索结果表")
@Data
public class RetrievalResults {
    /**
     * 检索结果ID
     */
    @ApiModelProperty(value = "检索结果ID")
    private Long id;

    /**
     * 消息ID
     */
    @ApiModelProperty(value = "消息ID")
    private Long messageId;

    /**
     * 文档块ID
     */
    @ApiModelProperty(value = "文档块ID")
    private Long chunkId;

    /**
     * 相关性得分
     */
    @ApiModelProperty(value = "相关性得分")
    private BigDecimal relevanceScore;

    /**
     * 排名
     */
    @ApiModelProperty(value = "排名")
    private Integer ranking;

    /**
     * 是否用于回答: 0-否,1-是
     */
    @ApiModelProperty(value = "是否用于回答: 0-否,1-是")
    private Integer usedInResponse;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
}
package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 反馈表
 */
@ApiModel(description = "反馈表")
@Data
public class Feedbacks {
    /**
     * 反馈ID
     */
    @ApiModelProperty(value = "反馈ID")
    private Long id;

    /**
     * 消息ID
     */
    @ApiModelProperty(value = "消息ID")
    private Long messageId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 评分: 1-5
     */
    @ApiModelProperty(value = "评分: 1-5")
    private Integer rating;

    /**
     * 反馈内容
     */
    @ApiModelProperty(value = "反馈内容")
    private String feedbackText;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
}
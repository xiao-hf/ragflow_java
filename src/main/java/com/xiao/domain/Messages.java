package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 消息表
 */
@ApiModel(description = "消息表")
@Data
public class Messages {
    /**
     * 消息ID
     */
    @ApiModelProperty(value = "消息ID")
    private Long id;

    /**
     * 会话ID
     */
    @ApiModelProperty(value = "会话ID")
    private Long conversationId;

    /**
     * 发送者类型: user, assistant, system
     */
    @ApiModelProperty(value = "发送者类型: user, assistant, system")
    private String senderType;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String content;

    /**
     * 消息token数量
     */
    @ApiModelProperty(value = "消息token数量")
    private Integer tokens;

    /**
     * 元数据(JSON格式)
     */
    @ApiModelProperty(value = "元数据(JSON格式)")
    private Object metadata;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
}
package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 文档表
 */
@ApiModel(description = "文档表")
@Data
public class Documents {
    /**
     * 文档ID
     */
    @ApiModelProperty(value = "文档ID")
    private Long id;

    /**
     * 知识库ID
     */
    @ApiModelProperty(value = "知识库ID")
    private Long knowledgeBaseId;

    /**
     * 上传者ID
     */
    @ApiModelProperty(value = "上传者ID")
    private Long uploaderId;

    /**
     * 文档标题
     */
    @ApiModelProperty(value = "文档标题")
    private String title;

    /**
     * 文档描述
     */
    @ApiModelProperty(value = "文档描述")
    private String description;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String fileName;

    /**
     * 文件大小(bytes)
     */
    @ApiModelProperty(value = "文件大小(bytes)")
    private Long fileSize;

    /**
     * 文件MIME类型
     */
    @ApiModelProperty(value = "文件MIME类型")
    private String mimeType;

    /**
     * 内容哈希值
     */
    @ApiModelProperty(value = "内容哈希值")
    private String contentHash;

    /**
     * 状态: 0-待处理,1-处理中,2-处理完成,3-处理失败
     */
    @ApiModelProperty(value = "状态: 0-待处理,1-处理中,2-处理完成,3-处理失败")
    private Integer status;

    /**
     * 向量化状态: 0-未向量化,1-向量化中,2-向量化完成,3-向量化失败
     */
    @ApiModelProperty(value = "向量化状态: 0-未向量化,1-向量化中,2-向量化完成,3-向量化失败")
    private Integer vectorStatus;

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

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
}
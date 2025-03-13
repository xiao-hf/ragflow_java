package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 文档块表
 */
@ApiModel(description="文档块表")
@Data
public class DocumentChunks {
    /**
    * 文档块ID
    */
    @ApiModelProperty(value="文档块ID")
    private Long id;

    /**
    * 文档ID
    */
    @ApiModelProperty(value="文档ID")
    private Long documentId;

    /**
    * 块索引
    */
    @ApiModelProperty(value="块索引")
    private Integer chunkIndex;

    /**
    * 块内容
    */
    @ApiModelProperty(value="块内容")
    private String content;

    /**
    * 元数据(JSON格式)
    */
    @ApiModelProperty(value="元数据(JSON格式)")
    private Object metadata;

    /**
    * 嵌入使用的模型
    */
    @ApiModelProperty(value="嵌入使用的模型")
    private String embeddingModel;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createdAt;
}
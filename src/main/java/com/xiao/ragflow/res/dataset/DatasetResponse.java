package com.xiao.ragflow.res.dataset;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 数据集响应数据
 */
@Data
public class DatasetResponse {
    /**
     * 数据集ID
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 数据集名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 数据集描述
     */
    @JSONField(name = "description")
    private String description;

    /**
     * 数据集创建者ID
     */
    @JSONField(name = "created_by")
    private String createdBy;

    /**
     * 数据集状态
     */
    @JSONField(name = "status")
    private String status;

    /**
     * 嵌入模型
     */
    @JSONField(name = "embedding_model")
    private String embeddingModel;

    /**
     * 分块方法
     */
    @JSONField(name = "chunk_method")
    private String chunkMethod;

    /**
     * 解析器配置
     */
    @JSONField(name = "parser_config")
    private Object parserConfig;

    /**
     * 块数量
     */
    @JSONField(name = "chunk_count")
    private Integer chunkCount;

    /**
     * 文档数量
     */
    @JSONField(name = "document_count")
    private Integer documentCount;

    /**
     * token数量
     */
    @JSONField(name = "token_num")
    private Integer tokenNum;

    /**
     * 创建时间戳
     */
    @JSONField(name = "create_time")
    private Long createTime;

    /**
     * 更新时间戳
     */
    @JSONField(name = "update_time")
    private Long updateTime;
} 
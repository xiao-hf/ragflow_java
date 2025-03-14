package com.xiao.ragflow.res.document;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 文档响应数据
 */
@Data
public class DocumentResponse {
    /**
     * 文档ID
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 数据集ID
     */
    @JSONField(name = "dataset_id")
    private String datasetId;

    /**
     * 创建者ID
     */
    @JSONField(name = "created_by")
    private String createdBy;

    /**
     * 文档存储位置
     */
    @JSONField(name = "location")
    private String location;

    /**
     * 文档名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 文档解析器配置
     */
    @JSONField(name = "parser_config")
    private Object parserConfig;

    /**
     * 文档状态（UNSTART/PARSING/FINISHED/ERROR）
     */
    @JSONField(name = "run")
    private String run;

    /**
     * 文档大小（字节）
     */
    @JSONField(name = "size")
    private Long size;

    /**
     * 文档缩略图
     */
    @JSONField(name = "thumbnail")
    private String thumbnail;

    /**
     * 文档类型
     */
    @JSONField(name = "type")
    private String type;

    /**
     * 分块方法
     */
    @JSONField(name = "chunk_method")
    private String chunkMethod;
} 
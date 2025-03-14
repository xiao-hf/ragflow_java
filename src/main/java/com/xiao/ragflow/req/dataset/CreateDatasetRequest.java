package com.xiao.ragflow.req.dataset;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建数据集请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateDatasetRequest extends BaseRequest {
    /**
     * 数据集名称（必填）
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 数据集头像，Base64编码
     */
    @JSONField(name = "avatar")
    private String avatar;

    /**
     * 数据集描述
     */
    @JSONField(name = "description")
    private String description;

    /**
     * 嵌入模型
     */
    @JSONField(name = "embedding_model")
    private String embeddingModel;

    /**
     * 权限（"me"或"team"）
     */
    @JSONField(name = "permission")
    private String permission;

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
} 
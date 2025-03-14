package com.xiao.ragflow.req.document;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 更新文档请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateDocumentRequest {

    /**
     * 文档名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 元数据字段
     */
    @JSONField(name = "meta_fields")
    private Map<String, Object> metaFields;

    /**
     * 分块方法
     */
    @JSONField(name = "chunk_method")
    private String chunkMethod;

    /**
     * 解析器配置
     */
    @JSONField(name = "parser_config")
    private Map<String, Object> parserConfig;
} 
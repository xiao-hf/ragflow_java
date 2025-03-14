package com.xiao.ragflow.res.retrieval;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 文档聚合数据
 */
@Data
public class DocAgg {
    /**
     * 文档数量
     */
    @JSONField(name = "count")
    private Integer count;

    /**
     * 文档ID
     */
    @JSONField(name = "doc_id")
    private String docId;

    /**
     * 文档名称
     */
    @JSONField(name = "doc_name")
    private String docName;
} 
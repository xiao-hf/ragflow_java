package com.xiao.ragflow.res.retrieval;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 检索响应数据
 */
@Data
public class RetrievalData {
    /**
     * chunk列表
     */
    @JSONField(name = "chunks")
    private List<ChunkData> chunks;

    /**
     * 文档聚合信息
     */
    @JSONField(name = "doc_aggs")
    private List<DocAgg> docAggs;

    /**
     * 总数
     */
    @JSONField(name = "total")
    private Integer total;
} 
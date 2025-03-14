package com.xiao.ragflow.req.retrieval;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 检索请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RetrievalRequest extends BaseRequest {
    /**
     * 查询问题（必需）
     */
    @JSONField(name = "question")
    private String question;

    /**
     * 数据集ID列表
     */
    @JSONField(name = "dataset_ids")
    private List<String> datasetIds;

    /**
     * 文档ID列表
     */
    @JSONField(name = "document_ids")
    private List<String> documentIds;

    /**
     * 页码
     */
    @JSONField(name = "page")
    private Integer page;

    /**
     * 每页大小
     */
    @JSONField(name = "page_size")
    private Integer pageSize;

    /**
     * 相似度阈值
     */
    @JSONField(name = "similarity_threshold")
    private Float similarityThreshold;

    /**
     * 向量相似度权重
     */
    @JSONField(name = "vector_similarity_weight")
    private Float vectorSimilarityWeight;

    /**
     * Top K
     */
    @JSONField(name = "top_k")
    private Integer topK;

    /**
     * 重排序模型ID
     */
    @JSONField(name = "rerank_id")
    private String rerankId;

    /**
     * 是否启用关键词搜索
     */
    @JSONField(name = "keyword")
    private Boolean keyword;

    /**
     * 是否高亮匹配词
     */
    @JSONField(name = "highlight")
    private Boolean highlight;
} 
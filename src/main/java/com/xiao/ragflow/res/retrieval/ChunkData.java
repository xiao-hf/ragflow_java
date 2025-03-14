package com.xiao.ragflow.res.retrieval;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 检索的Chunk数据
 */
@Data
public class ChunkData {
    /**
     * Chunk ID
     */
    @JSONField(name = "id")
    private String id;

    /**
     * Chunk内容
     */
    @JSONField(name = "content")
    private String content;

    /**
     * 文档ID
     */
    @JSONField(name = "document_id")
    private String documentId;

    /**
     * 文档关键词
     */
    @JSONField(name = "document_keyword")
    private String documentKeyword;

    /**
     * 高亮后的内容
     */
    @JSONField(name = "highlight")
    private String highlight;

    /**
     * 图片ID
     */
    @JSONField(name = "image_id")
    private String imageId;

    /**
     * 重要关键词
     */
    @JSONField(name = "important_keywords")
    private List<String> importantKeywords;

    /**
     * 知识库ID
     */
    @JSONField(name = "kb_id")
    private String kbId;

    /**
     * 位置信息
     */
    @JSONField(name = "positions")
    private List<String> positions;

    /**
     * 相似度分数
     */
    @JSONField(name = "similarity")
    private Float similarity;

    /**
     * 词项相似度
     */
    @JSONField(name = "term_similarity")
    private Float termSimilarity;

    /**
     * 向量相似度
     */
    @JSONField(name = "vector_similarity")
    private Float vectorSimilarity;
} 
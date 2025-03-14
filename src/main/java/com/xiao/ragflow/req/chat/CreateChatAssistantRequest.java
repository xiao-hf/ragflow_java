package com.xiao.ragflow.req.chat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 创建聊天助手请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateChatAssistantRequest {

    /**
     * 聊天助手名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 头像(Base64编码)
     */
    @JSONField(name = "avatar")
    private String avatar;

    /**
     * 数据集ID列表
     */
    @JSONField(name = "dataset_ids")
    private List<String> datasetIds;

    /**
     * LLM设置
     */
    @JSONField(name = "llm")
    private Map<String, Object> llm;

    /**
     * 提示词设置
     */
    @JSONField(name = "prompt")
    private Map<String, Object> prompt;
} 
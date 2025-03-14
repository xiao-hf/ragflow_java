package com.xiao.ragflow.res.chat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 会话消息响应数据模型
 */
@Data
public class MessageResponse {
    /**
     * 消息ID
     */
    @JSONField(name = "id")
    private String id;
    
    /**
     * 会话ID
     */
    @JSONField(name = "session_id")
    private String sessionId;
    
    /**
     * 消息角色（user/assistant/system）
     */
    @JSONField(name = "role")
    private String role;
    
    /**
     * 消息内容
     */
    @JSONField(name = "content")
    private String content;
    
    /**
     * 消息创建时间
     */
    @JSONField(name = "created_at")
    private String createdAt;
    
    /**
     * 引用的文档信息
     */
    @JSONField(name = "document_citations")
    private Object documentCitations;
    
    /**
     * 引用的工具调用信息
     */
    @JSONField(name = "tool_calls")
    private Object toolCalls;
    
    /**
     * 消息元数据
     */
    @JSONField(name = "metadata")
    private Object metadata;
} 
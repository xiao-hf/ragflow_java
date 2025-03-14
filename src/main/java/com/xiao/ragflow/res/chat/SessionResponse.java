package com.xiao.ragflow.res.chat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 会话响应数据模型
 */
@Data
public class SessionResponse {
    /**
     * 会话ID
     */
    @JSONField(name = "id")
    private String id;
    
    /**
     * 会话名称
     */
    @JSONField(name = "name")
    private String name;
    
    /**
     * 会话状态
     */
    @JSONField(name = "status")
    private String status;
    
    /**
     * 会话创建时间
     */
    @JSONField(name = "created_at")
    private String createdAt;
    
    /**
     * 会话更新时间
     */
    @JSONField(name = "updated_at")
    private String updatedAt;
    
    /**
     * 用户ID
     */
    @JSONField(name = "user_id")
    private String userId;
    
    /**
     * 助手ID
     */
    @JSONField(name = "assistant_id")
    private String assistantId;
} 
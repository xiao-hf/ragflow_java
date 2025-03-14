package com.xiao.ragflow.req.chat;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发送消息请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SendMessageRequest extends BaseRequest {
    /**
     * 会话ID
     */
    @JSONField(name = "session_id")
    private String sessionId;
    
    /**
     * 消息内容
     */
    @JSONField(name = "content")
    private String content;
    
    /**
     * 流式返回标志
     */
    @JSONField(name = "stream")
    private Boolean stream;
    
    /**
     * 重置会话历史标志
     */
    @JSONField(name = "reset_history")
    private Boolean resetHistory;
    
    /**
     * 携带检索标志
     */
    @JSONField(name = "with_retrieval")
    private Boolean withRetrieval;
} 
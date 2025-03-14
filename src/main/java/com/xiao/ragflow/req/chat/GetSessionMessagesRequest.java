package com.xiao.ragflow.req.chat;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取会话消息的请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GetSessionMessagesRequest extends BaseRequest {
    /**
     * 会话ID
     */
    @JSONField(name = "session_id")
    private String sessionId;
    
    /**
     * 分页页码
     */
    @JSONField(name = "page")
    private Integer page;
    
    /**
     * 每页大小
     */
    @JSONField(name = "page_size")
    private Integer pageSize;
} 
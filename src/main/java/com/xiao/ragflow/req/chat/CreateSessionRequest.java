package com.xiao.ragflow.req.chat;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建会话请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateSessionRequest extends BaseRequest {
    /**
     * 会话名称
     */
    @JSONField(name = "name")
    private String name;
    
    /**
     * 用户ID
     */
    @JSONField(name = "user_id")
    private String userId;
} 
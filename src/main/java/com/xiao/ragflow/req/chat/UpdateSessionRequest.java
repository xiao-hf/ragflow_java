package com.xiao.ragflow.req.chat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新会话请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateSessionRequest {

    /**
     * 会话名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 用户ID（可选）
     */
    @JSONField(name = "user_id")
    private String userId;
} 
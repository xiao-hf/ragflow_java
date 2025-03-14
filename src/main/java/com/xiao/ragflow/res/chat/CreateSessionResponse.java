package com.xiao.ragflow.res.chat;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.res.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建会话响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateSessionResponse extends BaseResponse {
    /**
     * 会话信息
     */
    @JSONField(name = "data")
    private SessionResponse data;
} 
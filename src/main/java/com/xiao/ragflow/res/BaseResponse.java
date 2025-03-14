package com.xiao.ragflow.res;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * RAGFlow API 基础响应类
 */
@Data
public class BaseResponse {
    /**
     * 响应码，0表示成功
     */
    @JSONField(name = "code")
    private Integer code;
    
    /**
     * 响应消息
     */
    @JSONField(name = "message")
    private String message;
} 
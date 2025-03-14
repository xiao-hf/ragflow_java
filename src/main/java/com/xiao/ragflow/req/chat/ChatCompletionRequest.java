package com.xiao.ragflow.req.chat;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 聊天完成请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChatCompletionRequest extends BaseRequest {
    /**
     * 模型名称
     */
    @JSONField(name = "model")
    private String model;

    /**
     * 消息列表
     */
    @JSONField(name = "messages")
    private List<ChatMessage> messages;

    /**
     * 是否流式输出
     */
    @JSONField(name = "stream")
    private Boolean stream;

    /**
     * 聊天消息
     */
    @Data
    public static class ChatMessage {
        /**
         * 角色（user/assistant/system）
         */
        @JSONField(name = "role")
        private String role;

        /**
         * 消息内容
         */
        @JSONField(name = "content")
        private String content;
    }
} 
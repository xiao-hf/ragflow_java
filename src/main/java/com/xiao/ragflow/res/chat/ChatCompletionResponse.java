package com.xiao.ragflow.res.chat;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 聊天完成响应
 */
@Data
public class ChatCompletionResponse {
    /**
     * 响应ID
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 对象类型
     */
    @JSONField(name = "object")
    private String object;

    /**
     * 创建时间戳
     */
    @JSONField(name = "created")
    private Long created;

    /**
     * 模型名称
     */
    @JSONField(name = "model")
    private String model;

    /**
     * 系统指纹
     */
    @JSONField(name = "system_fingerprint")
    private String systemFingerprint;

    /**
     * 选择结果
     */
    @JSONField(name = "choices")
    private List<Choice> choices;

    /**
     * 使用情况
     */
    @JSONField(name = "usage")
    private Usage usage;

    /**
     * 选择结果
     */
    @Data
    public static class Choice {
        /**
         * 索引
         */
        @JSONField(name = "index")
        private Integer index;

        /**
         * 消息
         */
        @JSONField(name = "message")
        private Message message;

        /**
         * 结束原因
         */
        @JSONField(name = "finish_reason")
        private String finishReason;

        /**
         * 流式响应中的增量消息
         */
        @JSONField(name = "delta")
        private Message delta;
    }

    /**
     * 消息
     */
    @Data
    public static class Message {
        /**
         * 角色
         */
        @JSONField(name = "role")
        private String role;

        /**
         * 内容
         */
        @JSONField(name = "content")
        private String content;
    }

    /**
     * 使用情况
     */
    @Data
    public static class Usage {
        /**
         * 提示token数
         */
        @JSONField(name = "prompt_tokens")
        private Integer promptTokens;

        /**
         * 完成token数
         */
        @JSONField(name = "completion_tokens")
        private Integer completionTokens;

        /**
         * 总token数
         */
        @JSONField(name = "total_tokens")
        private Integer totalTokens;
    }
} 
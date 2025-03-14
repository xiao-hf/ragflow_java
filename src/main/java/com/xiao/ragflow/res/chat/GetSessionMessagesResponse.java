package com.xiao.ragflow.res.chat;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.res.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取会话消息响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetSessionMessagesResponse extends BaseResponse {
    /**
     * 消息数据
     */
    @JSONField(name = "data")
    private MessageData data;
    
    @Data
    public static class MessageData {
        /**
         * 消息列表
         */
        @JSONField(name = "messages")
        private List<MessageResponse> messages;
        
        /**
         * 总消息数
         */
        @JSONField(name = "total")
        private Integer total;
    }
} 
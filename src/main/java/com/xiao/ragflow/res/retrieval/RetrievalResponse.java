package com.xiao.ragflow.res.retrieval;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.res.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 检索响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RetrievalResponse extends BaseResponse {
    /**
     * 检索数据
     */
    @JSONField(name = "data")
    private RetrievalData data;
} 
package com.xiao.ragflow.res.dataset;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.res.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 列出数据集响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListDatasetsResponse extends BaseResponse {
    /**
     * 数据集列表
     */
    @JSONField(name = "data")
    private List<DatasetResponse> data;
} 
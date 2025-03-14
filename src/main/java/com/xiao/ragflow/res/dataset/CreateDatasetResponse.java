package com.xiao.ragflow.res.dataset;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.res.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建数据集响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CreateDatasetResponse extends BaseResponse {
    /**
     * 数据集数据
     */
    @JSONField(name = "data")
    private DatasetResponse data;
} 
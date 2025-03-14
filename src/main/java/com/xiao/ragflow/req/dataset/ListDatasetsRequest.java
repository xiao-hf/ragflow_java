package com.xiao.ragflow.req.dataset;

import com.xiao.ragflow.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 列出数据集请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListDatasetsRequest extends BaseRequest {
    /**
     * 页码，默认为1
     */
    private Integer page = 1;

    /**
     * 每页条数，默认为30
     */
    private Integer pageSize = 30;

    /**
     * 排序字段，默认为create_time
     */
    private String orderby = "create_time";

    /**
     * 是否降序，默认为true
     */
    private Boolean desc = true;

    /**
     * 数据集名称
     */
    private String name;

    /**
     * 数据集ID
     */
    private String id;
} 
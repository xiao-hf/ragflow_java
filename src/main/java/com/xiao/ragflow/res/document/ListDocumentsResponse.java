package com.xiao.ragflow.res.document;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.res.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 文档列表响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ListDocumentsResponse extends BaseResponse {

    /**
     * 响应数据
     */
    @JSONField(name = "data")
    private DocumentsData data;

    @Data
    public static class DocumentsData {
        /**
         * 文档列表
         */
        @JSONField(name = "docs")
        private List<DocumentResponse> docs;

        /**
         * 总数量
         */
        @JSONField(name = "total")
        private Integer total;
    }
} 
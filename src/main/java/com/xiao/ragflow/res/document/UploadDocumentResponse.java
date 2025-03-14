package com.xiao.ragflow.res.document;

import com.alibaba.fastjson.annotation.JSONField;
import com.xiao.ragflow.res.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 上传文档响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UploadDocumentResponse extends BaseResponse {
    /**
     * 上传的文档列表
     */
    @JSONField(name = "data")
    private List<DocumentResponse> data;
} 
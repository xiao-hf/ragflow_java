package com.xiao.ragflow.req.document;

import com.xiao.ragflow.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文档请求
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UploadDocumentRequest extends BaseRequest {
    /**
     * 数据集ID
     */
    private String datasetId;
    
    /**
     * 文件列表
     */
    private MultipartFile[] files;
} 
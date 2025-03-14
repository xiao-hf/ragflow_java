package com.xiao.ragflow.res.document;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

/**
 * 文档信息响应
 */
@Data
public class DocumentResponse {

    /**
     * 文档ID
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 数据集ID
     */
    @JSONField(name = "dataset_id")
    private String datasetId;

    /**
     * 文档名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 文档位置
     */
    @JSONField(name = "location")
    private String location;

    /**
     * 文档大小
     */
    @JSONField(name = "size")
    private Long size;

    /**
     * 文档类型
     */
    @JSONField(name = "type")
    private String type;

    /**
     * 文档运行状态
     */
    @JSONField(name = "run")
    private String run;

    /**
     * 分块数量
     */
    @JSONField(name = "chunk_count")
    private Integer chunkCount;

    /**
     * 分块方法
     */
    @JSONField(name = "chunk_method")
    private String chunkMethod;

    /**
     * 解析器配置
     */
    @JSONField(name = "parser_config")
    private Map<String, Object> parserConfig;

    /**
     * 处理开始时间
     */
    @JSONField(name = "process_begin_at")
    private String processBeginAt;

    /**
     * 处理持续时间
     */
    @JSONField(name = "process_duation")
    private Double processDuration;

    /**
     * 进度
     */
    @JSONField(name = "progress")
    private Double progress;

    /**
     * 进度消息
     */
    @JSONField(name = "progress_msg")
    private String progressMsg;

    /**
     * 创建时间
     */
    @JSONField(name = "create_time")
    private Long createTime;

    /**
     * 创建日期
     */
    @JSONField(name = "create_date")
    private String createDate;

    /**
     * 更新时间
     */
    @JSONField(name = "update_time")
    private Long updateTime;

    /**
     * 更新日期
     */
    @JSONField(name = "update_date")
    private String updateDate;

    /**
     * 缩略图
     */
    @JSONField(name = "thumbnail")
    private String thumbnail;

    /**
     * Token数量
     */
    @JSONField(name = "token_count")
    private Integer tokenCount;

    /**
     * 创建者
     */
    @JSONField(name = "created_by")
    private String createdBy;

    /**
     * 来源类型
     */
    @JSONField(name = "source_type")
    private String sourceType;

    /**
     * 状态
     */
    @JSONField(name = "status")
    private String status;
} 
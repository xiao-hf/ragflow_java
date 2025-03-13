package com.xiao.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 知识库权限表
 */
@ApiModel(description = "知识库权限表")
@Data
public class KnowledgeBasePermissions {
    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    private Long id;

    /**
     * 知识库ID
     */
    @ApiModelProperty(value = "知识库ID")
    private Long knowledgeBaseId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 权限类型: read, write, admin
     */
    @ApiModelProperty(value = "权限类型: read, write, admin")
    private String permissionType;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
}
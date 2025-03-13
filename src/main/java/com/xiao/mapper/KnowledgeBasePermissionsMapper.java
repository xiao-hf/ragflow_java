package com.xiao.mapper;

import com.xiao.domain.KnowledgeBasePermissions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KnowledgeBasePermissionsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KnowledgeBasePermissions record);

    int insertSelective(KnowledgeBasePermissions record);

    KnowledgeBasePermissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnowledgeBasePermissions record);

    int updateByPrimaryKey(KnowledgeBasePermissions record);
}
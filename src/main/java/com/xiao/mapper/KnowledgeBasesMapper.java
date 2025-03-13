package com.xiao.mapper;

import com.xiao.domain.KnowledgeBases;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KnowledgeBasesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KnowledgeBases record);

    int insertSelective(KnowledgeBases record);

    KnowledgeBases selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KnowledgeBases record);

    int updateByPrimaryKey(KnowledgeBases record);
}
package com.xiao.mapper;

import com.xiao.domain.DocumentChunks;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocumentChunksMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DocumentChunks record);

    int insertSelective(DocumentChunks record);

    DocumentChunks selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DocumentChunks record);

    int updateByPrimaryKey(DocumentChunks record);
}
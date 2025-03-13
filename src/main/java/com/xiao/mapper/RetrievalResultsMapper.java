package com.xiao.mapper;

import com.xiao.domain.RetrievalResults;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RetrievalResultsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RetrievalResults record);

    int insertSelective(RetrievalResults record);

    RetrievalResults selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RetrievalResults record);

    int updateByPrimaryKey(RetrievalResults record);
}
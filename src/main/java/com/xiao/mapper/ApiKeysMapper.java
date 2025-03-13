package com.xiao.mapper;

import com.xiao.domain.ApiKeys;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiKeysMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ApiKeys record);

    int insertSelective(ApiKeys record);

    ApiKeys selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApiKeys record);

    int updateByPrimaryKey(ApiKeys record);
}
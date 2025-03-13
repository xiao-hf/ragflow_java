package com.xiao.mapper;

import com.xiao.domain.UsageStatistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsageStatisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UsageStatistics record);

    int insertSelective(UsageStatistics record);

    UsageStatistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UsageStatistics record);

    int updateByPrimaryKey(UsageStatistics record);
}
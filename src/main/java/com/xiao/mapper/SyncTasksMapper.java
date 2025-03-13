package com.xiao.mapper;

import com.xiao.domain.SyncTasks;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SyncTasksMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SyncTasks record);

    int insertSelective(SyncTasks record);

    SyncTasks selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SyncTasks record);

    int updateByPrimaryKey(SyncTasks record);
}
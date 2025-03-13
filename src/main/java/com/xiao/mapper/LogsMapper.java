package com.xiao.mapper;

import com.xiao.domain.Logs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Logs record);

    int insertSelective(Logs record);

    Logs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Logs record);

    int updateByPrimaryKey(Logs record);
}
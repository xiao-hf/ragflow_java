package com.xiao.mapper;

import com.xiao.domain.Messages;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessagesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Messages record);

    int insertSelective(Messages record);

    Messages selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKey(Messages record);
}
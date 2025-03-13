package com.xiao.mapper;

import com.xiao.domain.Conversations;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConversationsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Conversations record);

    int insertSelective(Conversations record);

    Conversations selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Conversations record);

    int updateByPrimaryKey(Conversations record);
}
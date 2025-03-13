package com.xiao.mapper;

import com.xiao.domain.Feedbacks;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbacksMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Feedbacks record);

    int insertSelective(Feedbacks record);

    Feedbacks selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Feedbacks record);

    int updateByPrimaryKey(Feedbacks record);
}
package com.xiao.mapper;

import com.xiao.domain.Documents;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocumentsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Documents record);

    int insertSelective(Documents record);

    Documents selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Documents record);

    int updateByPrimaryKey(Documents record);
}
package com.xiao.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.xiao.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    List<User> selectByUsername(@Param("username")String username);

    User selectOneByUsername(@Param("username")String username);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
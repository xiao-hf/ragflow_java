package com.xiao.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.xiao.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectOneByUsername(@Param("username")String username);

    List<User> selectByUsername(@Param("username")String username);

    List<User> selectByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
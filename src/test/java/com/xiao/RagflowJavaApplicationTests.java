package com.xiao;

import com.xiao.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RagflowJavaApplicationTests {
    @Resource
    UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(userMapper.selectByPrimaryKey(1L));
    }
}

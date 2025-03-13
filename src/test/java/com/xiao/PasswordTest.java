package com.xiao;

import com.xiao.utils.PasswordUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordTest {
    @Resource
    PasswordUtil passwordUtil;
    @Test
    public void test() {
        String password = "dsuifdsfjsad";
        for (int i = 0; i < 10; i++) {
            System.out.println(passwordUtil.encrypt(password));
        }
    }
}

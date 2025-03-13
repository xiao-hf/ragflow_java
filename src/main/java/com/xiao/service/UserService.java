package com.xiao.service;

import com.xiao.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务类，演示加密工具的使用
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private PasswordUtil passwordUtil;

    /**
     * 用户注册时加密密码
     *
     * @param username 用户名
     * @param password 原始密码
     * @return 是否注册成功
     */
    public boolean register(String username, String password) {
        // 对密码进行加密
        String encryptedPassword = passwordUtil.encrypt(password);
        
        // 打印加密后的密码（实际项目中应该保存到数据库）
        log.info("用户: {}, 加密后的密码: {}", username, encryptedPassword);
        
        // 这里应该将用户名和加密后的密码保存到数据库
        // userRepository.save(new User(username, encryptedPassword));
        
        return true;
    }

    /**
     * 用户登录验证
     *
     * @param username 用户名
     * @param password 输入的密码
     * @return 是否验证通过
     */
    public boolean login(String username, String password) {
        // 从数据库获取加密后的密码（这里是模拟，实际应该从数据库查询）
        String storedEncryptedPassword = getStoredPassword(username);
        
        if (storedEncryptedPassword == null) {
            return false;
        }
        
        // 验证密码
        return passwordUtil.verify(password, storedEncryptedPassword);
    }
    
    /**
     * 模拟从数据库获取存储的密码
     * 实际项目中应该从数据库查询
     */
    private String getStoredPassword(String username) {
        // 模拟数据库操作
        // 实际开发中应该是: return userRepository.findByUsername(username).getPassword();
        return null;
    }
} 
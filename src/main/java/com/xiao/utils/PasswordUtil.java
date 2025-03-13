package com.xiao.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 密码加密工具类
 */
@Component
public class PasswordUtil {

    @Value("${security.encrypt.salt}")
    private String salt;

    /**
     * 使用SHA-256算法+盐值进行单向加密
     *
     * @param plainPassword 明文密码
     * @return 加密后的密码
     */
    public String encrypt(String plainPassword) {
        try {
            // 将盐值和密码组合
            String passwordWithSalt = plainPassword + salt;
            
            // 创建SHA-256消息摘要实例
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // 计算摘要
            byte[] hashBytes = md.digest(passwordWithSalt.getBytes(StandardCharsets.UTF_8));
            
            // 转换为Base64编码
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("密码加密失败", e);
        }
    }

    /**
     * 验证密码是否匹配
     *
     * @param plainPassword 明文密码
     * @param encryptedPassword 加密的密码
     * @return 是否匹配
     */
    public boolean verify(String plainPassword, String encryptedPassword) {
        // 对明文密码进行加密，并与加密的密码比较
        String newEncrypted = encrypt(plainPassword);
        return newEncrypted.equals(encryptedPassword);
    }
} 
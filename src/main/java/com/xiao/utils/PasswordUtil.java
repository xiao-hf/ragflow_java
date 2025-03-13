package com.xiao.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密工具类
 */
@Component
public class PasswordUtil {

    @Value("${security.encrypt.salt}")
    private String salt;

    /**
     * 使用SHA-256算法+盐值进行单向加密
     *
     * @param plainText 明文
     * @return 加密后的字符串
     */
    public String encrypt(String plainText) {
        try {
            // 将盐值和明文组合
            String textWithSalt = plainText + salt;
            
            // 创建SHA-256消息摘要实例
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // 计算摘要
            byte[] hashBytes = md.digest(textWithSalt.getBytes(StandardCharsets.UTF_8));
            
            // 转换为Base64编码
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密失败", e);
        }
    }

    /**
     * 验证明文是否与加密值匹配
     *
     * @param plainText 明文
     * @param encrypted 加密值
     * @return 是否匹配
     */
    public boolean verify(String plainText, String encrypted) {
        // 对明文进行加密并与已加密值比较
        String newEncrypted = encrypt(plainText);
        return newEncrypted.equals(encrypted);
    }
} 
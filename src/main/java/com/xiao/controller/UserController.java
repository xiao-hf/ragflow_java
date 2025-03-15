package com.xiao.controller;

import com.xiao.common.Result;
import com.xiao.dto.LoginRequest;
import com.xiao.dto.RegisterRequest;
import com.xiao.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@Tag(name = "用户管理", description = "用户注册、登录等接口")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param registerRequest 注册请求
     * @param request 请求对象，用于获取IP地址
     * @return 注册结果
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "注册新用户")
    public Result<String> register(@RequestBody RegisterRequest registerRequest, HttpServletRequest request) {
        // 获取IP地址，优先使用前端提供的IP
        String ipAddress = registerRequest.getIpAddress();
        if (ipAddress == null || ipAddress.isEmpty()) {
            // 如果前端没有提供IP，才从请求头获取
            ipAddress = getClientIpAddress(request);
        }
        
        log.info("用户注册请求: {}, IP: {}", registerRequest.getUsername(), ipAddress);
        return userService.register(registerRequest, ipAddress);
    }

    /**
     * 用户登录
     *
     * @param loginRequest 登录请求
     * @param request 请求对象，用于获取IP地址
     * @return 登录结果
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录验证")
    public Result<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        // 获取IP地址，优先使用前端提供的IP
        String ipAddress = loginRequest.getIpAddress();
        if (ipAddress == null || ipAddress.isEmpty()) {
            // 如果前端没有提供IP，才从请求头获取
            ipAddress = getClientIpAddress(request);
        }
        
        log.info("用户登录请求: {}, IP: {}", loginRequest.getUsername(), ipAddress);
        return userService.login(loginRequest, ipAddress);
    }

    /**
     * 获取客户端真实IP地址
     *
     * @param request 请求对象
     * @return IP地址
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        // 如果是多级代理，取第一个IP地址
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        
        return ip;
    }
} 
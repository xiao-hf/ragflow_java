package com.xiao.service;

import com.xiao.common.Result;
import com.xiao.dto.LoginRequest;
import com.xiao.dto.RegisterRequest;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param registerRequest 注册请求
     * @param ipAddress 注册IP地址
     * @return 注册结果
     */
    Result<String> register(RegisterRequest registerRequest, String ipAddress);

    /**
     * 用户登录
     *
     * @param loginRequest 登录请求
     * @param ipAddress 登录IP地址
     * @return 登录结果
     */
    Result<String> login(LoginRequest loginRequest, String ipAddress);

}

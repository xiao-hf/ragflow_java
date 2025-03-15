package com.xiao.service.impl;

import com.xiao.common.Result;
import com.xiao.domain.User;
import com.xiao.dto.LoginRequest;
import com.xiao.dto.RegisterRequest;
import com.xiao.mapper.UserMapper;
import com.xiao.service.UserService;
import com.xiao.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户服务实现类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordUtil passwordUtil;

    /**
     * 用户注册
     *
     * @param registerRequest 注册请求
     * @param ipAddress 注册IP地址
     * @return 注册结果
     */
    @Override
    @Transactional
    public Result<String> register(RegisterRequest registerRequest, String ipAddress) {
        // 检查用户名是否已存在
        String username = registerRequest.getUsername();
        List<User> existingUsers = userMapper.selectByUsername(username);
        if (!existingUsers.isEmpty()) {
            return Result.fail("用户名已存在");
        }

        try {
            // 创建新用户对象
            User user = new User();
            user.setUsername(username);
            // 加密密码
            user.setPassword(passwordUtil.encrypt(registerRequest.getPassword()));
            user.setNickname(registerRequest.getNickname());
            user.setEmail(registerRequest.getEmail());
            
            // 设置默认值
            user.setRole("user");  // 默认普通用户角色
            user.setStatus(1);     // 默认启用状态
            
            // 设置时间
            Date now = new Date();
            user.setCreatedAt(now);
            user.setUpdatedAt(now);

            // 插入数据库
            userMapper.insert(user);
            
            // 清除敏感信息
            user.setPassword(null);
            
            log.info("用户注册成功: {}, IP: {}", user.getUsername(), ipAddress);
            return Result.success("注册成功");
        } catch (Exception e) {
            log.error("用户注册失败", e);
            return Result.fail("注册失败");
        }
    }

    /**
     * 用户登录
     *
     * @param loginRequest 登录请求
     * @param ipAddress    登录IP地址
     * @return 登录结果
     */
    @Override
    @Transactional
    public Result<String> login(LoginRequest loginRequest, String ipAddress) {
        try {
            // 根据用户名查询用户
            User user = userMapper.selectOneByUsername(loginRequest.getUsername());
            if (user == null) {
                return Result.fail("用户名不存在");
            }

            // 验证密码
            if (!passwordUtil.verify(loginRequest.getPassword(), user.getPassword())) {
                return Result.fail("密码错误");
            }

            // 检查用户状态
            if (user.getStatus() != 1) {
                return Result.fail("账号已被禁用");
            }

            // 更新登录信息
            Date now = new Date();
            user.setLastLoginTime(now);
            user.setLastLoginIp(ipAddress);
            userMapper.updateByPrimaryKey(user);

            // 清除敏感信息
            user.setPassword(null);

            log.info("用户登录成功: {}", user.getUsername());
            return Result.success("登录成功");
        } catch (Exception e) {
            log.error("用户登录失败", e);
            return Result.fail("登录失败");
        }
    }
}
